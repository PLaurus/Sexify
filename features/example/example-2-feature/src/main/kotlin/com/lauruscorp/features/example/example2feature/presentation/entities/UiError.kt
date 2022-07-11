package com.lauruscorp.features.example.example2feature.presentation.entities

internal sealed interface UiError {
	data class System(val throwable: Throwable) : UiError
	object NoA : UiError
	object NoB : UiError
	object NoOperation : UiError
}