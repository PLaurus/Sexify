package features.loading.domain.store

import com.arkivanov.mvikotlin.core.store.Store
import features.loading.entities.LoadingState

internal interface LoadingFeatureStore : Store<Any, LoadingFeatureStore.State, Any> {
	sealed interface Action {
		object StartLoading : Action
	}
	
	data class State(
		val loadingState: LoadingState
	)
	
	sealed interface Message {
		data class ChangeLoadingState(
			val state: LoadingState
		) : Message
	}
}