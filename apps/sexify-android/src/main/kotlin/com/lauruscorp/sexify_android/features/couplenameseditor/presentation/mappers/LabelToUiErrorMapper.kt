package com.lauruscorp.sexify_android.features.couplenameseditor.presentation.mappers

import com.lauruscorp.core.mapping.Mapper
import com.lauruscorp.features.couplenameseditordomain.store.CoupleNamesEditorStore
import com.lauruscorp.sexify_android.features.couplenameseditor.presentation.entities.UiError
import javax.inject.Inject

internal class LabelToUiErrorMapper @Inject constructor(
) : Mapper<CoupleNamesEditorStore.Label, UiError> {
    override fun map(from: CoupleNamesEditorStore.Label): UiError {
        return when (from) {
            is CoupleNamesEditorStore.Label.ExceptionError -> UiError.System(exception = from.exception)
        }
    }
}