package features.loading.domain.store

import com.arkivanov.mvikotlin.core.store.Reducer
import javax.inject.Inject

internal class LoadingFeatureReducer @Inject constructor(
) : Reducer<LoadingFeatureStore.State, LoadingFeatureStore.Message> {
	override fun LoadingFeatureStore.State.reduce(
		msg: LoadingFeatureStore.Message
	): LoadingFeatureStore.State {
		return when (msg) {
			is LoadingFeatureStore.Message.ChangeLoadingState -> copy(
				loadingState = msg.state
			)
		}
	}
}