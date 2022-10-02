package com.lauruscorp.features.menu_domain.store

import com.arkivanov.mvikotlin.core.store.Store

interface MenuStore : Store<MenuStore.Intent, MenuStore.State, MenuStore.Label> {
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