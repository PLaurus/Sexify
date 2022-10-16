package com.lauruscorp.features.gamedomain.store

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.lauruscorp.core.coroutines.CoroutineDispatchers
import javax.inject.Inject

internal class GameExecutor @Inject constructor(
    coroutineDispatchers: CoroutineDispatchers
) : CoroutineExecutor<GameStore.Intent, GameStore.Action, GameStore.State, GameStore.Message, GameStore.Label>(
    mainContext = coroutineDispatchers.main
) {
    override fun executeAction(action: GameStore.Action, getState: () -> GameStore.State) {
        when (action) {
            else -> {}
        }
    }

    override fun executeIntent(intent: GameStore.Intent, getState: () -> GameStore.State) {
        when (intent) {
            else -> {}
        }
    }
}