package m_package_1.presentation.mappers

import _M_PACKAGE_0_.store._M_NAME_PASCAL_Store
import com.lauruscorp.core.mapping.Mapper
import m_package_1.presentation.entities.UiError
import javax.inject.Inject

internal class LabelToUiErrorMapper @Inject constructor(
) : Mapper<_M_NAME_PASCAL_Store.Label, UiError> {
    override fun map(from: _M_NAME_PASCAL_Store.Label): UiError {
        return when (from) {
            is _M_NAME_PASCAL_Store.Label.ExceptionError -> UiError.System(exception = from.exception)
        }
    }
}