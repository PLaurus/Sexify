package com.lauruscorp.sexifyapp.features.home.presentation.entities

internal sealed interface UiError {
	data class System(val exception: Exception) : UiError
}