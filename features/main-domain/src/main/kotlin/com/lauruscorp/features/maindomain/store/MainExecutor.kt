package com.lauruscorp.features.maindomain.store

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.lauruscorp.core.coroutines.CoroutineDispatchers
import javax.inject.Inject

internal class MainExecutor @Inject constructor(
    coroutineDispatchers: CoroutineDispatchers
) : CoroutineExecutor<MainStore.Intent, MainStore.Action, MainStore.State, MainStore.Message, MainStore.Label>(
    mainContext = coroutineDispatchers.main
) {
    override fun executeAction(action: MainStore.Action, getState: () -> MainStore.State) {
        when (action) {
            else -> {}
        }
    }

    override fun executeIntent(intent: MainStore.Intent, getState: () -> MainStore.State) {
        when (intent) {
            else -> {}
        }
    }
}