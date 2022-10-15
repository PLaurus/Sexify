package com.lauruscorp.sexify_domain.loading.store

import com.arkivanov.mvikotlin.core.store.Store
import com.lauruscorp.sexify_domain.loading.entities.LoadingState

interface LoadingStore : Store<Any, LoadingStore.State, Any> {
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