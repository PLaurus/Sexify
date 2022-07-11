package com.lauruscorp.features.homedomain.store

import com.arkivanov.mvikotlin.core.store.Reducer
import javax.inject.Inject

internal class HomeReducer @Inject constructor(
) : Reducer<HomeStore.State, HomeStore.Message> {
	override fun HomeStore.State.reduce(msg: HomeStore.Message): HomeStore.State {
		return when (msg) {
			else -> copy(someValue = null)
		}
	}
}