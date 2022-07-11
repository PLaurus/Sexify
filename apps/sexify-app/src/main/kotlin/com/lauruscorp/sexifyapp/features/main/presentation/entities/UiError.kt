package com.lauruscorp.sexifyapp.features.main.presentation.entities

internal sealed interface UiError {
	data class System(val exception: Exception) : UiError
}