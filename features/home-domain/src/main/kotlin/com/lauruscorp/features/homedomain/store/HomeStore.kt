package com.lauruscorp.features.homedomain.store

import com.arkivanov.mvikotlin.core.store.Store

interface HomeStore : Store<HomeStore.Intent, HomeStore.State, HomeStore.Label> {
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