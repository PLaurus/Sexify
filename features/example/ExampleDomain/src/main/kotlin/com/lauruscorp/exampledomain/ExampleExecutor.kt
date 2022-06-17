package com.lauruscorp.exampledomain

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
			is ExampleStore.Intent.Calculate -> calculate(
				operation = intent.operation,
				a = intent.a,
				b = intent.b
			)
		}
	}
	
	private fun initialize(
		operation: Operation,
		a: Float,
		b: Float
	) {
		calculate(operation, a, b)
	}
	
	private fun calculate(
		operation: Operation,
		a: Float?,
		b: Float?
	) {
		val result = if (a != null && b != null) {
			try {
				when (operation) {
					Operation.None -> null
					Operation.Sum -> sum(a, b)
					Operation.Subtraction -> subtract(a, b)
					Operation.Multiplication -> multiply(a, b)
					Operation.Division -> divide(a, b)
				}
			} catch (arithmeticException: ArithmeticException) {
				publish(ExampleStore.Label.Error(arithmeticException))
				null
			}
		} else {
			null
		}
		
		dispatch(ExampleStore.Message.ResultIsCalculated(operation, a, b, result))
	}
	
	private fun sum(a: Float, b: Float): Float = a + b
	
	private fun subtract(a: Float, b: Float): Float = a - b
	
	private fun multiply(a: Float, b: Float): Float = a * b
	
	private fun divide(a: Float, b: Float): Float = a / b
}