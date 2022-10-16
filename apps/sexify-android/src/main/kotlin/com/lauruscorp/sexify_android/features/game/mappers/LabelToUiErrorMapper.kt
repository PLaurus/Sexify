package com.lauruscorp.sexify_android.features.game.mappers

import com.lauruscorp.core.mapping.Mapper
import com.lauruscorp.features.gamedomain.store.GameStore
import com.lauruscorp.sexify_android.features.game.entities.UiError
import javax.inject.Inject

internal class LabelToUiErrorMapper @Inject constructor(
) : Mapper<GameStore.Label, UiError> {
    override fun map(from: GameStore.Label): UiError {
        return when (from) {
            is GameStore.Label.ExceptionError -> UiError.System(exception = from.exception)
        }
    }
}