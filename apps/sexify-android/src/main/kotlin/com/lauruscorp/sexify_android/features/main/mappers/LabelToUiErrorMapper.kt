package com.lauruscorp.sexify_android.features.main.mappers

import com.lauruscorp.core_jvm.mapping.Mapper
import com.lauruscorp.features.maindomain.store.MainStore
import com.lauruscorp.sexify_android.features.main.entities.UiError
import javax.inject.Inject

internal class LabelToUiErrorMapper @Inject constructor(
) : Mapper<MainStore.Label, UiError> {
    override fun map(from: MainStore.Label): UiError {
        return when (from) {
            is MainStore.Label.ExceptionError -> UiError.System(exception = from.exception)
        }
    }
}