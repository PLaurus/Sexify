package com.lauruscorp.sexify_android.features.categoriesselection.entities

internal sealed interface UiError {
	data class System(val exception: Exception) : UiError
}