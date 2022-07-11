package com.lauruscorp.features.categoriesselectiondomain.store

import com.arkivanov.mvikotlin.core.store.Store

interface CategoriesSelectionStore : Store<CategoriesSelectionStore.Intent, CategoriesSelectionStore.State, CategoriesSelectionStore.Label> {
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