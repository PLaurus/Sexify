package com.lauruscorp.features.menu_domain.store

import com.arkivanov.mvikotlin.core.store.Reducer
import javax.inject.Inject

internal class MenuReducer @Inject constructor(
) : Reducer<MenuStore.State, MenuStore.Message> {
	override fun MenuStore.State.reduce(msg: MenuStore.Message): MenuStore.State {
		return when (msg) {
			else -> copy(someValue = null)
		}
	}
}