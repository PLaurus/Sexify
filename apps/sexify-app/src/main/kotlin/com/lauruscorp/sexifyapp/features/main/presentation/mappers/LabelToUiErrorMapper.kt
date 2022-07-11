package com.lauruscorp.sexifyapp.features.main.presentation.mappers

import com.lauruscorp.core.mapping.Mapper
import com.lauruscorp.features.maindomain.store.MainStore
import com.lauruscorp.sexifyapp.features.main.presentation.entities.UiError
import javax.inject.Inject

internal class LabelToUiErrorMapper @Inject constructor(
) : Mapper<MainStore.Label, UiError> {
	override fun map(from: MainStore.Label): UiError {
		return when (from) {
			is MainStore.Label.ExceptionError -> UiError.System(exception = from.exception)
		}
	}
}