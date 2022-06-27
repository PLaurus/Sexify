package _M_PACKAGE_.api

import _M_PACKAGE_.di.component._M_NAME_PASCAL_Component

class _M_NAME_PASCAL_DomainFactory(
	private val dependencies: _M_NAME_PASCAL_DomainDependencies
) {
	fun create(): _M_NAME_PASCAL_DomainApi {
		return _M_NAME_PASCAL_Component(
			dependencies = dependencies
		)
	}
}