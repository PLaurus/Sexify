package features.task_editor.presentation.entities

import androidx.compose.runtime.Immutable
import com.lauruscorp.sexify_data.languages.SexifyLanguage

@Immutable
data class TextTranslationFieldUiData(
	val language: SexifyLanguage,
	val text: String,
	val error: String? = null
)