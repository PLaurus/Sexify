package application.features.task_editor.data.mapping

import com.lauruscorp.sexify_data.entities.SexifyLanguage
import com.lauruscorp.sexify_data.entities.Task
import com.lauruscorp.sexify_data.entities.errors.TasksDataError
import features.task_editor.domain.entities.TaskEditorError
import features.task_editor.domain.entities.TaskEditorTask

internal fun Task.asTaskEditorTask(
    language: SexifyLanguage? = null
): TaskEditorTask {
    return TaskEditorTask(
        id = id,
        originalText = text.value,
        originalTextLanguage = text.language,
        textTranslations = textTranslations.associate { it.language to it.value },
        stage = stage.asTaskEditorTaskStage(language),
        doerSexes = doerSexes,
        partnerSexes = partnerSexes,
        timerSec = timerSec
    )
}

internal fun Task.Stage.asTaskEditorTaskStage(
    language: SexifyLanguage? = null
): TaskEditorTask.Stage {
    return TaskEditorTask.Stage(
        id = id,
        name = (language?.let(::getTranslatedName) ?: name).value,
        description = (language?.let(::getTranslatedDescription) ?: description)?.value,
    )
}

internal fun TasksDataError.asTaskEditorError(): TaskEditorError {
    return when (this) {
        TasksDataError.UnknownError -> TaskEditorError.DbError
        is TasksDataError.NotExists -> TaskEditorError.TaskError.NotExists(taskId)
        is TasksDataError.OriginalTextError.IncorrectDataForDb -> {
            TaskEditorError.OriginalTextError.IncorrectDataForDb(text, language)
        }
        is TasksDataError.OriginalTextError.DBLanguageDoesNotExist -> {
            TaskEditorError.OriginalTextError.DBLanguageDoesNotExist(language)
        }
        is TasksDataError.TranslationError.DbLanguageDoesNotExist -> {
            TaskEditorError.TranslationError.DbLanguageDoesNotExist(language)
        }
        is TasksDataError.TaskStageError.DbStageDoesNotExist -> {
            TaskEditorError.TaskStageError.DbStageDoesNotExist(stageId)
        }
        is TasksDataError.DoerSexesError.DbSexDoesNotExist -> {
            TaskEditorError.DoerSexesError.DbSexDoesNotExist(sex)
        }
        is TasksDataError.PartnerSexesError.DbSexDoesNotExist -> {
            TaskEditorError.PartnerSexesError.DbSexDoesNotExist(sex)
        }
    }
}