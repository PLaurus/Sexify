package features.task_editor.domain.entities

sealed interface LoadingState {
	object Loading : LoadingState
	object Loaded : LoadingState
	data class Error(val message: String) : LoadingState
}