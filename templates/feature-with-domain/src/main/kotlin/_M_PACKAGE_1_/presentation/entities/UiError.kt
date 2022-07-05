package _M_PACKAGE_1_.presentation.entities

sealed interface UiError {
	data class System(val throwable: Throwable) : UiError
}