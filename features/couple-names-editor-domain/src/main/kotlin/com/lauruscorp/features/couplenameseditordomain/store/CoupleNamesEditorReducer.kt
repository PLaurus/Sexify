package com.lauruscorp.features.couplenameseditordomain.store

import com.arkivanov.mvikotlin.core.store.Reducer
import javax.inject.Inject

internal class CoupleNamesEditorReducer @Inject constructor(
) : Reducer<CoupleNamesEditorStore.State, CoupleNamesEditorStore.Message> {
	override fun CoupleNamesEditorStore.State.reduce(msg: CoupleNamesEditorStore.Message): CoupleNamesEditorStore.State {
		return when (msg) {
			else -> copy(someValue = null)
		}
	}
}