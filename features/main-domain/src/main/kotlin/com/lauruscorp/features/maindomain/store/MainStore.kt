package com.lauruscorp.features.maindomain.store

import com.arkivanov.mvikotlin.core.store.Store

interface MainStore : Store<MainStore.Intent, MainStore.State, MainStore.Label> {
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