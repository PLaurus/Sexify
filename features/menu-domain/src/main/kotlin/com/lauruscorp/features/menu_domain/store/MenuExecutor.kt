package com.lauruscorp.features.menu_domain.store

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.lauruscorp.core.coroutines.CoroutineDispatchers
import javax.inject.Inject

internal class MenuExecutor @Inject constructor(
    coroutineDispatchers: CoroutineDispatchers
) : CoroutineExecutor<MenuStore.Intent, MenuStore.Action, MenuStore.State, MenuStore.Message, MenuStore.Label>(
    mainContext = coroutineDispatchers.main
) {
    override fun executeAction(action: MenuStore.Action, getState: () -> MenuStore.State) {
        when (action) {
            else -> {}
        }
    }

    override fun executeIntent(intent: MenuStore.Intent, getState: () -> MenuStore.State) {
        when (intent) {
            else -> {}
        }
    }
}