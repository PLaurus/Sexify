package com.lauruscorp.features.gamedomain.store

import com.arkivanov.mvikotlin.core.store.Store

interface GameStore : Store<GameStore.Intent, GameStore.State, GameStore.Label> {
	sealed interface Intent
	
	sealed interface Action
	
	data class State(
		val someValue: Any?
	)
	
	sealed interface Message
	
	sealed interface Label {
		data class ExceptionError(val exception: Exception) : Label
	}
}