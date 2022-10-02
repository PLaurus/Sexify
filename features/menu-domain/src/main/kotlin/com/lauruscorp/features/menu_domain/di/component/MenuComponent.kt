package com.lauruscorp.features.menu_domain.di.component

import com.lauruscorp.features.menu_domain.api.MenuDomainApi
import com.lauruscorp.features.menu_domain.api.MenuDomainDependencies
import com.lauruscorp.features.menu_domain.di.modules.store.MenuStoreModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
	dependencies = [
		MenuDomainDependencies::class
	],
	modules = [
		MenuStoreModule::class
	]
)
internal interface MenuComponent : MenuDomainApi {
	@Component.Factory
	interface Factory {
		fun create(
			dependencies: MenuDomainDependencies
		): MenuComponent
	}
	
	companion object {
		operator fun invoke(
			dependencies: MenuDomainDependencies
		): MenuComponent {
			return DaggerMenuComponent.factory()
				.create(dependencies)
		}
	}
}