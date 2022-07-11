package _M_PACKAGE_1_.presentation.entities

internal sealed interface UiError {
	data class System(val exception: Exception) : UiError
}