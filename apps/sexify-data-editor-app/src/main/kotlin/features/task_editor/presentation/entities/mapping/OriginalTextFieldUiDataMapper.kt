package features.task_editor.presentation.entities.mapping

import features.task_editor.domain.entities.TaskEditorError
import features.task_editor.domain.store.TaskEditorStore
import features.task_editor.presentation.entities.OriginalTextFieldUiData

fun TaskEditorStore.State.toOriginalTextFieldData(): OriginalTextFieldUiData {
	return OriginalTextFieldUiData(
		text = originalText,
		languageName = originalTextLanguage.name,
		error = getOriginalTextError()
	)
}

private fun TaskEditorStore.State.getOriginalTextError(): String? {
	val error = errors.filterIsInstance<TaskEditorError.OriginalTextError>()
		.firstOrNull() ?: return null
	
	
	return when (error) {
		is TaskEditorError.OriginalTextError.Empty -> "Поле не может быть пустым"
		is TaskEditorError.OriginalTextError.IncorrectDataForDb -> {
			"Некорректные данные для базы данных: language(${error.language.tag}), text(${error.text})"
		}
		is TaskEditorError.OriginalTextError.DBLanguageDoesNotExist -> {
			"В базе данных отсутствует язык текста: ${error.language}"
		}
	}
}