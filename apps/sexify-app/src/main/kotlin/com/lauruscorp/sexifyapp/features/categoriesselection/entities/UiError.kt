package com.lauruscorp.sexifyapp.features.categoriesselection.entities

internal sealed interface UiError {
	data class System(val exception: Exception) : UiError
}