package com.lauruscorp.features.example.exampledomain.store

import com.arkivanov.mvikotlin.core.store.Store
import com.lauruscorp.features.example.exampledomain.entities.Operation

interface ExampleStore : Store<ExampleStore.Intent, ExampleStore.State, ExampleStore.Label> {
	sealed interface Intent {
		data class UpdateOperation(
			val operation: Operation
		) : Intent
		
		data class UpdateA(
			val a: Int?
		) : Intent
		
		data class UpdateB(
			val b: Int?
		) : Intent
		
		object Calculate : Intent
	}
	
	sealed interface Action {
		data class Initialize(
			val operation: Operation,
			val a: Int,
			val b: Int
		) : Action
	}
	
	data class State(
		val operation: Operation = Operation.None,
		val a: Int? = null,
		val b: Int? = null,
		val result: Float? = null
	)
	
	sealed interface Message {
		data class NewA(val a: Int?) : Message
		data class NewB(val b: Int?) : Message
		data class NewOperation(val operation: Operation) : Message
		
		data class ResultIsCalculated(
			val operation: Operation,
			val a: Int?,
			val b: Int?,
			val result: Float?
		) : Message
	}
	
	sealed interface Label {
		data class ThrowableError(val throwable: Throwable) : Label
		
		sealed interface CalculationIsImpossible : Label {
			object NoA : CalculationIsImpossible
			object NoB : CalculationIsImpossible
			object NoOperation : CalculationIsImpossible
		}
	}
}