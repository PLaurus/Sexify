package _M_PACKAGE_.di.component

import _M_PACKAGE_.api._M_NAME_PASCAL_DomainApi
import _M_PACKAGE_.api._M_NAME_PASCAL_DomainDependencies
import _M_PACKAGE_.di.modules.store._M_NAME_PASCAL_StoreModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
	dependencies = [
		_M_NAME_PASCAL_DomainDependencies::class
	],
	modules = [
		_M_NAME_PASCAL_StoreModule::class
	]
)
internal interface _M_NAME_PASCAL_Component : _M_NAME_PASCAL_DomainApi {
	@Component.Factory
	interface Factory {
		fun create(
			dependencies: _M_NAME_PASCAL_DomainDependencies
		): _M_NAME_PASCAL_Component
	}
	
	companion object {
		operator fun invoke(
			dependencies: _M_NAME_PASCAL_DomainDependencies
		): _M_NAME_PASCAL_Component {
			return Dagger_M_NAME_PASCAL_Component.factory()
				.create(dependencies)
		}
	}
}