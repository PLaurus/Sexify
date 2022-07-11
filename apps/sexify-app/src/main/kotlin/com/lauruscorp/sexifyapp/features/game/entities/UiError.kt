package com.lauruscorp.sexifyapp.features.game.entities

internal sealed interface UiError {
	data class System(val exception: Exception) : UiError
}