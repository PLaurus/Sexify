package features.task_editor.presentation.entities

import androidx.compose.runtime.Immutable

@Immutable
data class OriginalTextFieldUiData(
	val text: String,
	val languageName: String,
	val error: String? = null
)