package features.loading.domain.store

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.lauruscorp.core.coroutines.CoroutineDispatchers
import features.loading.domain.initializers.Initializer
import features.loading.entities.LoadingState
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class LoadingFeatureExecutor @Inject constructor(
	private val coroutineDispatchers: CoroutineDispatchers,
	private val initializers: Set<@JvmSuppressWildcards Initializer>
) : CoroutineExecutor<Any, LoadingFeatureStore.Action, LoadingFeatureStore.State, LoadingFeatureStore.Message, Any>(
	mainContext = coroutineDispatchers.main
) {
	override fun executeAction(
		action: LoadingFeatureStore.Action,
		getState: () -> LoadingFeatureStore.State
	) {
		when (action) {
			LoadingFeatureStore.Action.StartLoading -> startLoading()
		}
	}
	
	private fun startLoading() {
		dispatch(LoadingFeatureStore.Message.ChangeLoadingState(LoadingState.Loading))
		
		scope.launch(coroutineDispatchers.default) {
			initializeApp()
			dispatch(LoadingFeatureStore.Message.ChangeLoadingState(LoadingState.Loaded))
		}
	}
	
	private suspend fun initializeApp() {
		initializers.forEach {
			it.initialize()
		}
	}
}