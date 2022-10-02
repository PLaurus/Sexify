package com.lauruscorp.sexify_android.features.couplenameseditor.entities

internal sealed interface UiError {
	data class System(val exception: Exception) : UiError
}