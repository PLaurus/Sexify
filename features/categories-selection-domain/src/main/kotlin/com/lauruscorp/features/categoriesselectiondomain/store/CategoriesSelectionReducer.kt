package com.lauruscorp.features.categoriesselectiondomain.store

import com.arkivanov.mvikotlin.core.store.Reducer
import javax.inject.Inject

internal class CategoriesSelectionReducer @Inject constructor(
) : Reducer<CategoriesSelectionStore.State, CategoriesSelectionStore.Message> {
	override fun CategoriesSelectionStore.State.reduce(msg: CategoriesSelectionStore.Message): CategoriesSelectionStore.State {
		return when (msg) {
			else -> copy(someValue = null)
		}
	}
}