package com.lauruscorp.sexify_android.features.menu.entities

internal sealed interface UiError {
	data class System(val exception: Exception) : UiError
}