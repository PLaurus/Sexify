package com.lauruscorp.exampledomain

import androidx.annotation.MainThread
import kotlinx.coroutines.flow.Flow

interface ExampleDomain {
	val state: State
	val states: Flow<State>
	val events: Flow<Event>
	
	@MainThread
	fun accept(intent: Intent)
	
	data class State(
		val operation: Operation,
		val a: Float?,
		val b: Float?,
		val result: Float?
	)
	
	sealed interface Event {
		data class Error(val throwable: Throwable) : Event
	}
	
	sealed interface Intent {
		data class Sum(
			val a: Float,
			val b: Float
		) : Intent
		
		data class Subtraction(
			val a: Float,
			val b: Float
		) : Intent
		
		data class Division(
			val a: Float,
			val b: Float
		) : Intent
	}
	
	enum class Operation {
		None,
		Sum,
		Subtraction,
		Division
	}
}