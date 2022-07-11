package com.lauruscorp.features.categoriesselectiondomain.di.component

import com.lauruscorp.features.categoriesselectiondomain.api.CategoriesSelectionDomainApi
import com.lauruscorp.features.categoriesselectiondomain.api.CategoriesSelectionDomainDependencies
import com.lauruscorp.features.categoriesselectiondomain.di.modules.store.CategoriesSelectionStoreModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
	dependencies = [
		CategoriesSelectionDomainDependencies::class
	],
	modules = [
		CategoriesSelectionStoreModule::class
	]
)
internal interface CategoriesSelectionComponent : CategoriesSelectionDomainApi {
	@Component.Factory
	interface Factory {
		fun create(
			dependencies: CategoriesSelectionDomainDependencies
		): CategoriesSelectionComponent
	}
	
	companion object {
		operator fun invoke(
			dependencies: CategoriesSelectionDomainDependencies
		): CategoriesSelectionComponent {
			return DaggerCategoriesSelectionComponent.factory()
				.create(dependencies)
		}
	}
}