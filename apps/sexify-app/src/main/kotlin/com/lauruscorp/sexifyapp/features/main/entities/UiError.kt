package com.lauruscorp.sexifyapp.features.main.entities

internal sealed interface UiError {
	data class System(val exception: Exception) : UiError
}