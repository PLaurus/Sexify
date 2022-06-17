package com.lauruscorp.exampledomain

import com.arkivanov.mvikotlin.core.store.Store
import com.lauruscorp.exampledomain.entities.Operation

interface ExampleStore : Store<ExampleStore.Intent, ExampleStore.State, ExampleStore.Label> {
	sealed interface Intent {
		data class Calculate(
			val operation: Operation,
			val a: Float?,
			val b: Float?
		) : Intent
	}
	
	sealed interface Action {
		data class Initialize(
			val operation: Operation,
			val a: Float,
			val b: Float
		) : Action
	}
	
	data class State(
		val operation: Operation = Operation.None,
		val a: Float? = null,
		val b: Float? = null,
		val result: Float? = null
	)
	
	sealed interface Message {
		data class ResultIsCalculated(
			val operation: Operation,
			val a: Float?,
			val b: Float?,
			val result: Float?
		) : Message
	}
	
	sealed interface Label {
		data class Error(val throwable: Throwable) : Label
	}
}