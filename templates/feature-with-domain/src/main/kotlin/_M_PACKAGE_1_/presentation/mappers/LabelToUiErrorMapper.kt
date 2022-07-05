package _M_PACKAGE_1_.presentation.mappers

import _M_PACKAGE_0_.store._M_NAME_PASCAL_Store
import _M_PACKAGE_1_.presentation.entities.UiError
import com.lauruscorp.core.mapping.Mapper
import javax.inject.Inject

internal class LabelToUiErrorMapper @Inject constructor(
) : Mapper<_M_NAME_PASCAL_Store.Label, UiError> {
	override fun map(from: _M_NAME_PASCAL_Store.Label): UiError {
		return when (from) {
			is _M_NAME_PASCAL_Store.Label.ThrowableError -> UiError.System(throwable = from.throwable)
		}
	}
}