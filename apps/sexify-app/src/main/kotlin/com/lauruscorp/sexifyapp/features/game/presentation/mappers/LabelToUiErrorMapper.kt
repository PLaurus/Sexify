package com.lauruscorp.sexifyapp.features.game.presentation.mappers

import com.lauruscorp.core.mapping.Mapper
import com.lauruscorp.features.gamedomain.store.GameStore
import com.lauruscorp.sexifyapp.features.game.presentation.entities.UiError
import javax.inject.Inject

internal class LabelToUiErrorMapper @Inject constructor(
) : Mapper<GameStore.Label, UiError> {
	override fun map(from: GameStore.Label): UiError {
		return when (from) {
			is GameStore.Label.ExceptionError -> UiError.System(exception = from.exception)
		}
	}
}