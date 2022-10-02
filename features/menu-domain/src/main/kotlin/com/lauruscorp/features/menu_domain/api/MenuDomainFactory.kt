package com.lauruscorp.features.menu_domain.api

import com.lauruscorp.features.menu_domain.di.component.MenuComponent

class MenuDomainFactory(
	private val dependencies: MenuDomainDependencies
) {
	fun create(): MenuDomainApi {
		return MenuComponent(
			dependencies = dependencies
		)
	}
}