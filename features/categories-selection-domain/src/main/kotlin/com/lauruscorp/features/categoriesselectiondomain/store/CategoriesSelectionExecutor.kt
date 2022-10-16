package com.lauruscorp.features.categoriesselectiondomain.store

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.lauruscorp.core.coroutines.CoroutineDispatchers
import javax.inject.Inject

internal class CategoriesSelectionExecutor @Inject constructor(
    coroutineDispatchers: CoroutineDispatchers
) : CoroutineExecutor<CategoriesSelectionStore.Intent, CategoriesSelectionStore.Action, CategoriesSelectionStore.State, CategoriesSelectionStore.Message, CategoriesSelectionStore.Label>(
    mainContext = coroutineDispatchers.main
) {
    override fun executeAction(
        action: CategoriesSelectionStore.Action,
        getState: () -> CategoriesSelectionStore.State
    ) {
        when (action) {
            else -> {}
        }
    }

    override fun executeIntent(
        intent: CategoriesSelectionStore.Intent,
        getState: () -> CategoriesSelectionStore.State
    ) {
        when (intent) {
            else -> {}
        }
    }
}