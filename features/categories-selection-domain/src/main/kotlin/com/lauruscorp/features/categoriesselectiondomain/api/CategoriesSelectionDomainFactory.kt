package com.lauruscorp.features.categoriesselectiondomain.api

import com.lauruscorp.features.categoriesselectiondomain.di.component.CategoriesSelectionComponent

class CategoriesSelectionDomainFactory(
	private val dependencies: CategoriesSelectionDomainDependencies
) {
	fun create(): CategoriesSelectionDomainApi {
		return CategoriesSelectionComponent(
			dependencies = dependencies
		)
	}
}