package com.lauruscorp.sexifyapp.features.game.presentation.entities

internal sealed interface UiError {
	data class System(val exception: Exception) : UiError
}