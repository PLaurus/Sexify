package com.lauruscorp.exampledomain

import com.arkivanov.mvikotlin.core.store.Reducer
import javax.inject.Inject

internal class ExampleReducer @Inject constructor(
) : Reducer<ExampleStore.State, ExampleStore.Message> {
	override fun ExampleStore.State.reduce(msg: ExampleStore.Message): ExampleStore.State {
		return when (msg) {
			is ExampleStore.Message.ResultIsCalculated -> copy(
				operation = msg.operation,
				a = msg.a,
				b = msg.b,
				result = msg.result
			)
		}
	}
}