package com.lauruscorp.features.gamedomain.store

import com.arkivanov.mvikotlin.core.store.Reducer
import javax.inject.Inject

internal class GameReducer @Inject constructor(
) : Reducer<GameStore.State, GameStore.Message> {
	override fun GameStore.State.reduce(msg: GameStore.Message): GameStore.State {
		return when (msg) {
			else -> copy(someValue = null)
		}
	}
}