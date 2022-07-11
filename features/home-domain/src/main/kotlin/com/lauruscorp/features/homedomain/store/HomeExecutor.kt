package com.lauruscorp.features.homedomain.store

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.lauruscorp.core.coroutines.CoroutineDispatchers
import javax.inject.Inject

internal class HomeExecutor @Inject constructor(
	coroutineDispatchers: CoroutineDispatchers
) : CoroutineExecutor<HomeStore.Intent, HomeStore.Action, HomeStore.State, HomeStore.Message, HomeStore.Label>(
	mainContext = coroutineDispatchers.main
) {
	override fun executeAction(action: HomeStore.Action, getState: () -> HomeStore.State) {
		when (action) {
			else -> {}
		}
	}
	
	override fun executeIntent(intent: HomeStore.Intent, getState: () -> HomeStore.State) {
		when (intent) {
			else -> {}
		}
	}
}