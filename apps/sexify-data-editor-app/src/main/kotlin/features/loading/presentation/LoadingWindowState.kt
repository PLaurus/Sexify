package features.loading.presentation

import androidx.compose.runtime.*
import com.arkivanov.mvikotlin.extensions.coroutines.states
import features.loading.dependencies.LoadingFeatureDependencies
import features.loading.di.component.LoadingFeatureComponent
import features.loading.entities.LoadingState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Stable
class LoadingWindowState(
	dependencies: LoadingFeatureDependencies,
	val scope: CoroutineScope,
	val onLoadingFinished: () -> Unit,
	val onCloseRequest: () -> Unit = {}
) {
	private val loadingFeatureComponent = LoadingFeatureComponent(dependencies)
	
	private val loadingFeatureStore = loadingFeatureComponent.getLoadingFeatureStore()
	private val initialStoreState = loadingFeatureComponent.getLoadingFeatureStoreInitialState()
	
	private var _loadingState by mutableStateOf(initialStoreState.loadingState)
	val loadingState: LoadingState
		get() = _loadingState
	
	init {
		scope.launch {
			loadingFeatureStore.states
				.collect { state ->
					_loadingState = state.loadingState
					
					if (_loadingState == LoadingState.Loaded) {
						onLoadingFinished()
					}
				}
		}
	}
}

@Composable
fun rememberLoadingFeatureWindowState(
	dependencies: LoadingFeatureDependencies,
	scope: CoroutineScope,
	onLoadingFinished: () -> Unit,
	onCloseRequest: () -> Unit = {}
) = remember {
	LoadingWindowState(
		dependencies = dependencies,
		scope = scope,
		onLoadingFinished = onLoadingFinished,
		onCloseRequest = onCloseRequest
	)
}