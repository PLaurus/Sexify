package com.lauruscorp.features.maindomain.store

import com.arkivanov.mvikotlin.core.store.Reducer
import javax.inject.Inject

internal class MainReducer @Inject constructor(
) : Reducer<MainStore.State, MainStore.Message> {
	override fun MainStore.State.reduce(msg: MainStore.Message): MainStore.State {
		return when (msg) {
			else -> copy(someValue = null)
		}
	}
}