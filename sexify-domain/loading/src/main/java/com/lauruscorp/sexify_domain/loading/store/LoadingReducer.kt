package com.lauruscorp.sexify_domain.loading.store

import com.arkivanov.mvikotlin.core.store.Reducer
import javax.inject.Inject

internal class LoadingReducer @Inject constructor(
) : Reducer<LoadingStore.State, LoadingStore.Message> {
    override fun LoadingStore.State.reduce(
        msg: LoadingStore.Message
    ): LoadingStore.State {
        return when (msg) {
            is LoadingStore.Message.ChangeLoadingState -> copy(
                loadingState = msg.state
            )
        }
    }
}