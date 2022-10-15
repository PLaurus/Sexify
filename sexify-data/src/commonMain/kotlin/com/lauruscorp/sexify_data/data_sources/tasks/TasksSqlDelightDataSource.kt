package com.lauruscorp.sexify_data.data_sources.tasks

import com.lauruscorp.core.util.Result
import com.lauruscorp.core.util.getOrElse
import com.lauruscorp.sexify_data.data_sources.mapping.asTaskStage
import com.lauruscorp.sexify_data.data_sources.mapping.toTask
import com.lauruscorp.sexify_data.databases.tasks.TasksDatabase
import com.lauruscorp.sexify_data.databases.tasks.utils.*
import com.lauruscorp.sexify_data.entities.SexifyLanguage
import com.lauruscorp.sexify_data.entities.SexifySex
import com.lauruscorp.sexify_data.entities.Task
import com.lauruscorp.sexify_data.entities.Text
import com.lauruscorp.sexify_data.entities.errors.TasksDataError
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class TasksSqlDelightDataSource(
    private val database: TasksDatabase,
    private val ioDispatcher: CoroutineDispatcher
) : TasksLocalDataSource {
    override suspend fun insert(
        text: Text,
        textTranslations: List<Text>,
        stageId: Long,
        doerSexes: List<SexifySex>,
        partnerSexes: List<SexifySex>,
        timerSec: Int?
    ): Result<Task, TasksDataError> = withContext(ioDispatcher) {
        database.transactionWithResult {
            val textContentId = insertOrUpdateTextContentFields(
                text = text.value,
                language = text.language
            ).getOrElse { rollback(Result.failure(it)) }

            insertOrUpdateTextTranslations(
                textContentId = textContentId,
                translations = textTranslations.associate { it.language to it.value }
            )

            val taskStage = database.selectTaskStageById(stageId)
                ?.asTaskStage(database)
                ?: rollback(Result.failure(TasksDataError.TaskStageError.DbStageDoesNotExist(stageId)))

            val taskId = database.insertTaskFields(
                textId = textContentId,
                taskStageId = taskStage.id,
                timer = timerSec
            ) ?: rollback(Result.failure(TasksDataError.UnknownError))

            insertTaskDoerSexes(
                taskId = taskId,
                sexes = doerSexes
            )

            insertTaskDoerPartnerSexes(
                taskId = taskId,
                sexes = partnerSexes
            )

            Result.success(
                Task(
                    id = taskId,
                    text = text,
                    textTranslations = textTranslations,
                    stage = taskStage,
                    doerSexes = doerSexes,
                    partnerSexes = partnerSexes,
                    timerSec = timerSec
                )
            )
        }
    }

    override suspend fun getAll(): Result<List<Task>, TasksDataError> = withContext(ioDispatcher) {
        database.transactionWithResult {
            val tasks = database.selectAllTasks()
                .mapNotNull { it.toTask(database, useTransaction = false) }

            Result.success(tasks)
        }
    }

    override fun getAllFlow(): Flow<Result<List<Task>, TasksDataError>> {
        return database.flowSelectAllTasks()
            .map { dbTasks ->
                database.transactionWithResult {
                    val tasks = dbTasks.mapNotNull { it.toTask(database, useTransaction = false) }
                    Result.success<List<Task>, TasksDataError>(tasks)
                }
            }
            .flowOn(ioDispatcher)
    }

    override suspend fun get(id: Long): Result<Task?, TasksDataError> = withContext(ioDispatcher) {
        database.transactionWithResult {
            val dbTask = database.selectTaskById(id)
                ?.toTask(database, useTransaction = false)

            Result.success(dbTask)
        }
    }

    override suspend fun update(
        id: Long,
        text: Text,
        textTranslations: List<Text>,
        stageId: Long,
        doerSexes: List<SexifySex>,
        partnerSexes: List<SexifySex>,
        timerSec: Int?
    ): Result<Task, TasksDataError> = withContext(ioDispatcher) {
        database.transactionWithResult {
            val existingDbTask = database.selectTaskById(id)
                ?: rollback(Result.failure(TasksDataError.NotExists(id)))

            val textContentId = insertOrUpdateTextContentFields(
                text = text.value,
                language = text.language,
                textContentId = existingDbTask.textId
            ).getOrElse { rollback(Result.failure(it)) }

            insertOrUpdateTextTranslations(
                textContentId = textContentId,
                translations = textTranslations.associate { it.language to it.value }
            )

            val taskStage = database.selectTaskStageById(stageId)
                ?.asTaskStage(database)
                ?: rollback(Result.failure(TasksDataError.TaskStageError.DbStageDoesNotExist(stageId)))

            database.updateTaskById(
                id = existingDbTask.id,
                textId = textContentId,
                taskStageId = taskStage.id,
                timer = timerSec
            )

            insertTaskDoerSexes(
                taskId = existingDbTask.id,
                sexes = doerSexes
            )

            insertTaskDoerPartnerSexes(
                taskId = existingDbTask.id,
                sexes = partnerSexes
            )

            Result.success(
                Task(
                    id = id,
                    text = text,
                    textTranslations = textTranslations,
                    stage = taskStage,
                    doerSexes = doerSexes,
                    partnerSexes = partnerSexes,
                    timerSec = timerSec
                )
            )
        }
    }

    override suspend fun delete(id: Long) {
        database.transaction {
            val correspondingTask = database.selectTaskById(id) ?: rollback()
            val textContentId = correspondingTask.textId

            database.deleteTranslationsByTextContentId(textContentId)
            database.deleteTaskDoerSexesByTaskId(id)
            database.deleteTaskDoerPartnerSexesByTaskId(id)
            database.deleteTaskById(id)
            database.deleteTextContentById(textContentId)
        }
    }

    private fun insertOrUpdateTextContentFields(
        text: String,
        language: SexifyLanguage,
        textContentId: Long? = null
    ): Result<Long, TasksDataError> {
        val existingTextContent = if (textContentId != null) {
            database.selectTextContentById(id = textContentId)
        } else {
            null
        }

        if (!checkDbLanguageExists(language.tag)) {
            return Result.failure(TasksDataError.OriginalTextError.DBLanguageDoesNotExist(language))
        }

        if (existingTextContent == null) {
            val newTextContentId = database.insertTextContentFields(
                originalText = text,
                originalLanguageId = language.tag
            ) ?: return Result.failure(
                TasksDataError.OriginalTextError.IncorrectDataForDb(
                    text = text,
                    language = language
                )
            )

            return Result.success(newTextContentId)
        } else {
            database.updateTextContentById(
                id = existingTextContent.id,
                originalText = text,
                originalLanguageId = language.tag
            )

            return Result.success(existingTextContent.id)
        }
    }

    private fun insertOrUpdateTextTranslations(
        textContentId: Long,
        translations: Map<SexifyLanguage, String>
    ): Result<Unit, TasksDataError> {
        val existingDbTranslations = database.selectTranslationsByTextContentId(textContentId)

        for ((language, translation) in translations) {
            val correspondingDbTranslation =
                existingDbTranslations.firstOrNull { it.languageId == language.tag }

            when {
                correspondingDbTranslation == null -> {
                    if (!checkDbLanguageExists(language.tag)) {
                        return Result.failure(
                            TasksDataError.TranslationError.DbLanguageDoesNotExist(language)
                        )
                    }

                    if (translation.isNotBlank()) {
                        database.insertTranslationFields(
                            textContentId = textContentId,
                            languageId = language.tag,
                            translation = translation
                        )
                    }
                }
                translation.isBlank() -> {
                    database.deleteTranslationById(id = correspondingDbTranslation.id)
                }
                else -> {
                    database.updateTranslationBy(
                        textContentId = correspondingDbTranslation.textContentId,
                        languageId = correspondingDbTranslation.languageId,
                        translation = translation
                    )
                }
            }
        }

        return Result.success(Unit)
    }

    private fun insertTaskDoerSexes(
        taskId: Long,
        sexes: List<SexifySex>
    ): Result<Unit, TasksDataError> {
        database.deleteTaskDoerSexesByTaskId(taskId)

        sexes.forEach { sex ->
            val correspondingSex = database.selectSexByEnumName(enumName = sex.name)
                ?: return Result.failure(TasksDataError.DoerSexesError.DbSexDoesNotExist(sex))

            database.insertTaskDoerSexFields(
                taskId = taskId,
                sexId = correspondingSex.id
            )
        }

        return Result.success(Unit)
    }

    private fun insertTaskDoerPartnerSexes(
        taskId: Long,
        sexes: List<SexifySex>
    ): Result<Unit, TasksDataError> {
        database.deleteTaskDoerPartnerSexesByTaskId(taskId)

        sexes.forEach { sex ->
            val correspondingSex = database.selectSexByEnumName(enumName = sex.name)
                ?: return Result.failure(TasksDataError.PartnerSexesError.DbSexDoesNotExist(sex))

            database.insertTaskDoerPartnerSexFields(
                taskId = taskId,
                sexId = correspondingSex.id
            )
        }

        return Result.success(Unit)
    }

    private fun checkDbLanguageExists(
        languageTag: String
    ): Boolean {
        return database.selectLanguageById(languageTag) != null
    }
}