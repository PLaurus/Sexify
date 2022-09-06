package m_package_1.presentation.entities

internal sealed interface UiError {
    data class System(val exception: Exception) : UiError
}