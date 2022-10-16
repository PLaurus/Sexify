package com.lauruscorp.features.couplenameseditordomain.store

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.lauruscorp.core.coroutines.CoroutineDispatchers
import javax.inject.Inject

internal class CoupleNamesEditorExecutor @Inject constructor(
    coroutineDispatchers: CoroutineDispatchers
) : CoroutineExecutor<CoupleNamesEditorStore.Intent, CoupleNamesEditorStore.Action, CoupleNamesEditorStore.State, CoupleNamesEditorStore.Message, CoupleNamesEditorStore.Label>(
    mainContext = coroutineDispatchers.main
) {
    override fun executeAction(
        action: CoupleNamesEditorStore.Action,
        getState: () -> CoupleNamesEditorStore.State
    ) {
        when (action) {
            else -> {}
        }
    }

    override fun executeIntent(
        intent: CoupleNamesEditorStore.Intent,
        getState: () -> CoupleNamesEditorStore.State
    ) {
        when (intent) {
            else -> {}
        }
    }
}