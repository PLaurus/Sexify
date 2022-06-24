package com.lauruscorp.features.example.example2feature.presentation.mappers

import com.lauruscorp.core.mapping.Mapper
import com.lauruscorp.features.example.example2feature.presentation.entities.UiError
import com.lauruscorp.features.example.exampledomain.store.ExampleStore
import javax.inject.Inject

internal class LabelToUiErrorMapper @Inject constructor(
) : Mapper<ExampleStore.Label, UiError> {
	override fun map(from: ExampleStore.Label): UiError {
		return when (from) {
			is ExampleStore.Label.ThrowableError -> UiError.System(throwable = from.throwable)
			is ExampleStore.Label.CalculationIsImpossible.NoA -> UiError.NoA
			is ExampleStore.Label.CalculationIsImpossible.NoB -> UiError.NoB
			is ExampleStore.Label.CalculationIsImpossible.NoOperation -> UiError.NoOperation
		}
	}
}