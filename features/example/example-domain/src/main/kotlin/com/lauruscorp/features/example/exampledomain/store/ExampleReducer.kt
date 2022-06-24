package com.lauruscorp.features.example.exampledomain.store

import com.arkivanov.mvikotlin.core.store.Reducer
import javax.inject.Inject

internal class ExampleReducer @Inject constructor(
) : Reducer<ExampleStore.State, ExampleStore.Message> {
	override fun ExampleStore.State.reduce(msg: ExampleStore.Message): ExampleStore.State {
		return when (msg) {
			is ExampleStore.Message.NewA -> copy(
				a = msg.a
			)
			is ExampleStore.Message.NewB -> copy(
				b = msg.b
			)
			is ExampleStore.Message.NewOperation -> copy(
				operation = msg.operation
			)
			is ExampleStore.Message.ResultIsCalculated -> copy(
				operation = msg.operation,
				a = msg.a,
				b = msg.b,
				result = msg.result
			)
		}
	}
}