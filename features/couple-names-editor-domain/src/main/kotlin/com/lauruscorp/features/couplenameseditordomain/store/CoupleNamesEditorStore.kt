package com.lauruscorp.features.couplenameseditordomain.store

import com.arkivanov.mvikotlin.core.store.Store

interface CoupleNamesEditorStore : Store<CoupleNamesEditorStore.Intent, CoupleNamesEditorStore.State, CoupleNamesEditorStore.Label> {
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