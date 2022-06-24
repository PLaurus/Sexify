package com.lauruscorp.exampledomain.store

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.lauruscorp.core.coroutines.CoroutineDispatchers
import com.lauruscorp.exampledomain.entities.Operation
import javax.inject.Inject

internal class ExampleExecutor @Inject constructor(
	coroutineDispatchers: CoroutineDispatchers
) : CoroutineExecutor<ExampleStore.Intent, ExampleStore.Action, ExampleStore.State, ExampleStore.Message, ExampleStore.Label>(
	mainContext = coroutineDispatchers.main
) {
	override fun executeAction(action: ExampleStore.Action, getState: () -> ExampleStore.State) {
		when (action) {
			is ExampleStore.Action.Initialize -> initialize(
				operation = action.operation,
				a = action.a,
				b = action.b
			)
		}
	}
	
	override fun executeIntent(intent: ExampleStore.Intent, getState: () -> ExampleStore.State) {
		when (intent) {
			is ExampleStore.Intent.UpdateA -> updateA(
				a = intent.a,
				getState = getState
			)
			is ExampleStore.Intent.UpdateB -> updateB(
				b = intent.b,
				getState = getState
			)
			is ExampleStore.Intent.UpdateOperation -> updateOperation(
				operation = intent.operation,
				getState = getState
			)
			is ExampleStore.Intent.Calculate -> calculate(
				state = getState()
			)
		}
	}
	
	private fun initialize(
		operation: Operation,
		a: Int,
		b: Int
	) {
		calculate(a, b, operation)
	}
	
	private fun updateA(
		a: Int?,
		getState: () -> ExampleStore.State
	) {
		dispatch(ExampleStore.Message.NewA(a))
		calculate(state = getState())
	}
	
	private fun updateB(
		b: Int?,
		getState: () -> ExampleStore.State
	) {
		dispatch(ExampleStore.Message.NewB(b))
		calculate(state = getState())
	}
	
	private fun updateOperation(
		operation: Operation,
		getState: () -> ExampleStore.State
	) {
		dispatch(ExampleStore.Message.NewOperation(operation))
		calculate(state = getState())
	}
	
	private fun calculate(
		a: Int?,
		b: Int?,
		operation: Operation
	) {
		val result = when {
			a == null -> {
				publish(ExampleStore.Label.CalculationIsImpossible.NoA)
				null
			}
			b == null -> {
				publish(ExampleStore.Label.CalculationIsImpossible.NoB)
				null
			}
			operation == Operation.None -> {
				publish(ExampleStore.Label.CalculationIsImpossible.NoOperation)
				null
			}
			else -> {
				try {
					when (operation) {
						Operation.None -> null
						Operation.Sum -> sum(a, b)
						Operation.Subtraction -> subtract(a, b)
						Operation.Multiplication -> multiply(a, b)
						Operation.Division -> divide(a, b)
					}
				} catch (arithmeticException: ArithmeticException) {
					publish(ExampleStore.Label.ThrowableError(arithmeticException))
					null
				}
			}
		}
		
		dispatch(
			ExampleStore.Message.ResultIsCalculated(
				operation = operation,
				a = a,
				b = b,
				result = result
			)
		)
	}
	
	private fun calculate(state: ExampleStore.State) {
		calculate(
			a = state.a,
			b = state.b,
			operation = state.operation
		)
	}
	
	private fun sum(a: Int, b: Int): Float = a.toFloat() + b
	
	private fun subtract(a: Int, b: Int): Float = a.toFloat() - b
	
	private fun multiply(a: Int, b: Int): Float = a.toFloat() * b
	
	private fun divide(a: Int, b: Int): Float = a.toFloat() / b
}