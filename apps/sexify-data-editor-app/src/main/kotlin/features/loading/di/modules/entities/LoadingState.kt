package features.loading.di.modules.entities

sealed interface LoadingState {
	object Loading : LoadingState
	object Loaded : LoadingState
	data class Error(val errorMessage: String) : LoadingState
}