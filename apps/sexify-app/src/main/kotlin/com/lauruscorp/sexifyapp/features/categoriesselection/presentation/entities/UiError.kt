package com.lauruscorp.sexifyapp.features.categoriesselection.presentation.entities

internal sealed interface UiError {
	data class System(val exception: Exception) : UiError
}