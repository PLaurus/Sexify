package application.features.task_editor.data.repository

import application.BuildConfig
import application.features.task_editor.data.mapping.toTaskEditorFeatureTaskStage
import com.lauruscorp.core_jvm.coroutines.CoroutineDispatchers
import com.lauruscorp.sexify_data.database.SexifyDatabase
import com.lauruscorp.sexify_data.database.aliases.DbTask
import com.lauruscorp.sexify_data.database.aliases.DbTranslation
import com.lauruscorp.sexify_data.database.utils.deleteTaskById
import com.lauruscorp.sexify_data.database.utils.deleteTaskDoerPartnerSexesByTaskId
import com.lauruscorp.sexify_data.database.utils.deleteTaskDoerSexesByTaskId
import com.lauruscorp.sexify_data.database.utils.deleteTextContentById
import com.lauruscorp.sexify_data.database.utils.deleteTranslationById
import com.lauruscorp.sexify_data.database.utils.deleteTranslationsByTextContentId
import com.lauruscorp.sexify_data.database.utils.getLanguage
import com.lauruscorp.sexify_data.database.utils.getOriginalLanguage
import com.lauruscorp.sexify_data.database.utils.getOriginalText
import com.lauruscorp.sexify_data.database.utils.insertTaskDoerPartnerSexFields
import com.lauruscorp.sexify_data.database.utils.insertTaskDoerSexFields
import com.lauruscorp.sexify_data.database.utils.insertTaskFields
import com.lauruscorp.sexify_data.database.utils.insertTextContentFields
import com.lauruscorp.sexify_data.database.utils.insertTranslationFields
import com.lauruscorp.sexify_data.database.utils.selectLanguageById
import com.lauruscorp.sexify_data.database.utils.selectSexByEnumName
import com.lauruscorp.sexify_data.database.utils.selectTaskById
import com.lauruscorp.sexify_data.database.utils.selectTaskDoerPartnerSexesByTaskId
import com.lauruscorp.sexify_data.database.utils.selectTaskDoerSexesByTaskId
import com.lauruscorp.sexify_data.database.utils.selectTaskStageById
import com.lauruscorp.sexify_data.database.utils.selectTextContentById
import com.lauruscorp.sexify_data.database.utils.selectTranslationsByTextContentId
import com.lauruscorp.sexify_data.database.utils.updateTaskById
import com.lauruscorp.sexify_data.database.utils.updateTextContentById
import com.lauruscorp.sexify_data.database.utils.updateTranslationBy
import com.lauruscorp.sexify_data.languages.SexifyLanguage
import com.lauruscorp.sexify_data.mapping.toSexifyLanguage
import com.lauruscorp.sexify_data.mapping.toSexifySex
import com.lauruscorp.sexify_data.sex.SexifySex
import com.squareup.sqldelight.TransactionWithReturn
import features.task_editor.domain.entities.Task
import features.task_editor.domain.entities.TaskEditorError
import features.task_editor.domain.repository.TasksRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class TaskEditorFeatureTasksRepository @Inject constructor(
	private val database: SexifyDatabase,
	private val coroutineDispatchers: CoroutineDispatchers
) : TasksRepository {
	override suspend fun getTaskById(
		id: Long,
		language: SexifyLanguage?
	): Task? = withContext(coroutineDispatchers.io) {
		database.transactionWithResult {
			val dbTask = database.selectTaskById(id) ?: rollback(null)
			
			dbTask.mapToTask(database, languageTag = language?.tag)
		}
	}
	
	override suspend fun saveTask(
		task: Task
	): TaskEditorError? = withContext(coroutineDispatchers.io) {
		database.transactionWithResult {
			val existingDbTask = if (task.id != null) {
				database.selectTaskById(id = task.id)
			} else {
				null
			}
			
			if (existingDbTask != null) {
				updateTask(existingDbTask, task)
			} else {
				insertNewTask(task)
			}
			
			null
		}
	}
	
	override suspend fun deleteTask(taskId: Long) {
		database.transaction {
			val correspondingTask = database.selectTaskById(taskId) ?: rollback()
			val textContentId = correspondingTask.textId
			
			database.deleteTranslationsByTextContentId(textContentId)
			database.deleteTaskDoerSexesByTaskId(taskId)
			database.deleteTaskDoerPartnerSexesByTaskId(taskId)
			database.deleteTaskById(taskId)
			database.deleteTextContentById(textContentId)
		}
	}
	
	private fun TransactionWithReturn<TaskEditorError?>.insertNewTask(
		task: Task
	) {
		val textContentId = insertOrUpdateTextContentFields(
			originalText = task.originalText,
			originalLanguage = task.originalTextLanguage
		)
		
		insertOrUpdateTextTranslations(
			textContentId = textContentId,
			translations = task.textTranslations
		)
		
		val taskStageId = task.stage?.id ?: rollback(TaskEditorError.TaskStageError.NotSelected)
		if (!checkDbTaskStageExists(taskStageId)) {
			rollback(TaskEditorError.TaskStageError.DbStageDoesNotExist(taskStageId))
		}
		
		val taskId = database.insertTaskFields(
			textId = textContentId,
			taskStageId = taskStageId,
			timer = task.timerSec
		) ?: rollback(TaskEditorError.DbError)
		
		insertTaskDoerSexes(
			taskId = taskId,
			sexes = task.doerSexes
		)
		
		insertTaskDoerPartnerSexes(
			taskId = taskId,
			sexes = task.partnerSexes
		)
	}
	
	private fun TransactionWithReturn<TaskEditorError?>.updateTask(
		existingDbTask: DbTask,
		task: Task
	) {
		val textContentId = insertOrUpdateTextContentFields(
			originalText = task.originalText,
			originalLanguage = task.originalTextLanguage,
			textContentId = existingDbTask.textId
		)
		
		insertOrUpdateTextTranslations(
			textContentId = textContentId,
			translations = task.textTranslations
		)
		
		val taskStageId = task.stage?.id ?: rollback(TaskEditorError.TaskStageError.NotSelected)
		if (!checkDbTaskStageExists(taskStageId)) {
			rollback(TaskEditorError.TaskStageError.DbStageDoesNotExist(taskStageId))
		}
		
		database.updateTaskById(
			id = existingDbTask.id,
			textId = textContentId,
			taskStageId = taskStageId,
			timer = task.timerSec
		)
		
		insertTaskDoerSexes(
			taskId = existingDbTask.id,
			sexes = task.doerSexes
		)
		
		insertTaskDoerPartnerSexes(
			taskId = existingDbTask.id,
			sexes = task.partnerSexes
		)
	}
	
	private fun TransactionWithReturn<TaskEditorError?>.insertOrUpdateTextContentFields(
		originalText: String,
		originalLanguage: SexifyLanguage,
		textContentId: Long? = null
	): Long {
		val existingTextContent = if (textContentId != null) {
			database.selectTextContentById(id = textContentId)
		} else {
			null
		}
		
		if (!checkDbLanguageExists(originalLanguage.tag)) {
			rollback(TaskEditorError.OriginalTextError.DBLanguageDoesNotExist(originalLanguage))
		}
		
		if (existingTextContent == null) {
			return database.insertTextContentFields(
				originalText = originalText,
				originalLanguageId = originalLanguage.tag
			) ?: rollback(
				TaskEditorError.OriginalTextError.IncorrectDataForDb(
					text = originalText,
					language = originalLanguage
				)
			)
		} else {
			database.updateTextContentById(
				id = existingTextContent.id,
				originalText = originalText,
				originalLanguageId = originalLanguage.tag
			)
			
			return existingTextContent.id
		}
	}
	
	private fun TransactionWithReturn<TaskEditorError?>.insertOrUpdateTextTranslations(
		textContentId: Long,
		translations: Map<SexifyLanguage, String>
	) {
		val existingDbTranslations = database.selectTranslationsByTextContentId(textContentId)
		
		for ((language, translation) in translations) {
			val correspondingDbTranslation = existingDbTranslations.firstOrNull { it.languageId == language.tag }
			
			when {
				correspondingDbTranslation == null -> {
					if (!checkDbLanguageExists(language.tag)) {
						rollback(TaskEditorError.TranslationError.DbLanguageDoesNotExist(language))
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
	}
	
	private fun TransactionWithReturn<TaskEditorError?>.insertTaskDoerSexes(
		taskId: Long,
		sexes: List<SexifySex>
	) {
		database.deleteTaskDoerSexesByTaskId(taskId)
		
		sexes.forEach { sex ->
			val correspondingSex = database.selectSexByEnumName(enumName = sex.name)
				?: rollback(TaskEditorError.DoerSexesError.DbSexDoesNotExist(sex))
			
			database.insertTaskDoerSexFields(
				taskId = taskId,
				sexId = correspondingSex.id
			)
		}
	}
	
	private fun TransactionWithReturn<TaskEditorError?>.insertTaskDoerPartnerSexes(
		taskId: Long,
		sexes: List<SexifySex>
	) {
		database.deleteTaskDoerPartnerSexesByTaskId(taskId)
		
		sexes.forEach { sex ->
			val correspondingSex = database.selectSexByEnumName(enumName = sex.name)
				?: rollback(TaskEditorError.PartnerSexesError.DbSexDoesNotExist(sex))
			
			database.insertTaskDoerPartnerSexFields(
				taskId = taskId,
				sexId = correspondingSex.id
			)
		}
	}
	
	private fun checkDbLanguageExists(
		languageTag: String
	): Boolean {
		return database.selectLanguageById(languageTag) != null
	}
	
	private fun checkDbTaskStageExists(
		taskStageId: Long
	): Boolean {
		return database.selectTaskStageById(taskStageId) != null
	}
	
	private fun DbTask.mapToTask(
		database: SexifyDatabase,
		languageTag: String? = null
	): Task {
		return Task(
			id = id,
			originalText = database.getOriginalText(textId) ?: "",
			originalTextLanguage = database.selectTextContentById(textId)
				?.getOriginalLanguage(database)
				?.toSexifyLanguage()
				?: BuildConfig.DEFAULT_SEXIFY_LANGUAGE,
			textTranslations = database.selectTranslationsByTextContentId(textId)
				.toMap(),
			stage = database.selectTaskStageById(taskStageId)
				?.toTaskEditorFeatureTaskStage(database, languageTag),
			doerSexes = database.selectTaskDoerSexesByTaskId(id)
				.mapNotNull { it.toSexifySex(database) },
			partnerSexes = database.selectTaskDoerPartnerSexesByTaskId(id)
				.mapNotNull { it.toSexifySex(database) },
			timerSec = timer
		)
	}
	
	private fun List<DbTranslation>.toMap(): Map<SexifyLanguage, String> {
		val result = mutableMapOf<SexifyLanguage, String>()
		
		for (dbTranslation in this) {
			val dbLanguage = dbTranslation.getLanguage(database) ?: continue
			val language = dbLanguage.toSexifyLanguage() ?: continue
			val translation = dbTranslation.translation
			result += language to translation
		}
		
		return result
	}
}