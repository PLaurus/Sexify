package com.lauruscorp.sexify_domain.loading.store

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.lauruscorp.core.coroutines.CoroutineDispatchers
import com.lauruscorp.sexify_domain.loading.entities.LoadingState
import com.lauruscorp.sexify_domain.loading.initializer.ExternalInitializer
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class LoadingExecutor @Inject constructor(
    private val coroutineDispatchers: CoroutineDispatchers,
    private val initializer: ExternalInitializer
) : CoroutineExecutor<Any, LoadingStore.Action, LoadingStore.State, LoadingStore.Message, Any>(
    mainContext = coroutineDispatchers.main
) {
    override fun executeAction(
        action: LoadingStore.Action,
        getState: () -> LoadingStore.State
    ) {
        when (action) {
            LoadingStore.Action.StartLoading -> startLoading()
        }
    }

    private fun startLoading() {
        dispatch(LoadingStore.Message.ChangeLoadingState(LoadingState.Loading))

        scope.launch {
            withContext(coroutineDispatchers.default) {
                initializer.initialize()
            }
            dispatch(LoadingStore.Message.ChangeLoadingState(LoadingState.Loaded))
        }
    }
}