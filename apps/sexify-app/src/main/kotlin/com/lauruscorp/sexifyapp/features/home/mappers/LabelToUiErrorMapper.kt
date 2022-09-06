package com.lauruscorp.sexifyapp.features.home.mappers

import com.lauruscorp.core_jvm.mapping.Mapper
import com.lauruscorp.features.homedomain.store.HomeStore
import com.lauruscorp.sexifyapp.features.home.entities.UiError
import javax.inject.Inject

internal class LabelToUiErrorMapper @Inject constructor(
) : Mapper<HomeStore.Label, UiError> {
    override fun map(from: HomeStore.Label): UiError {
        return when (from) {
            is HomeStore.Label.ExceptionError -> UiError.System(exception = from.exception)
        }
    }
}