package features.task_editor.presentation.entities.mapping

import com.lauruscorp.sexify_data.languages.SexifyLanguage
import features.task_editor.domain.entities.TaskEditorError
import features.task_editor.domain.store.TaskEditorStore
import features.task_editor.presentation.entities.TextTranslationFieldUiData

fun TaskEditorStore.State.toTextTranslationFieldsUiData(): List<TextTranslationFieldUiData> {
	val result = mutableListOf<TextTranslationFieldUiData>()
	
	for (translationEntry in textTranslations.entries) {
		result += TextTranslationFieldUiData(
			language = translationEntry.key,
			text = translationEntry.value,
			error = getTextTranslationError(language = translationEntry.key)
		)
	}
	
	return result
}

private fun TaskEditorStore.State.getTextTranslationError(
	language: SexifyLanguage
): String? {
	val error = errors.filterIsInstance<TaskEditorError.TranslationError>()
		.firstOrNull { it.language == language } ?: return null
	
	return when (error) {
		is TaskEditorError.TranslationError.DbLanguageDoesNotExist -> {
			"Язык не содержится в базе данных"
		}
	}
}
