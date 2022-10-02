package com.lauruscorp.sexify_android.features.categoriesselection.di.modules.domain

import com.lauruscorp.features.categoriesselectiondomain.api.CategoriesSelectionDomainApi
import com.lauruscorp.features.categoriesselectiondomain.api.CategoriesSelectionDomainDependencies
import com.lauruscorp.features.categoriesselectiondomain.api.CategoriesSelectionDomainFactory
import com.lauruscorp.features.categoriesselectiondomain.store.CategoriesSelectionStore
import com.lauruscorp.sexify_android.features.categoriesselection.di.component.CategoriesSelectionFeatureComponent
import com.lauruscorp.sexify_android.features.main.di.component.scope.MainActivityScope
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal interface DomainModule {
	@Binds
	fun provideCategoriesSelectionDomainDependencies(
		categoriesSelectionFeatureComponent: CategoriesSelectionFeatureComponent
	): CategoriesSelectionDomainDependencies
	
	companion object {
		@Provides
		fun provideCategoriesSelectionDomainFactory(
			dependencies: CategoriesSelectionDomainDependencies
		): CategoriesSelectionDomainFactory {
			return CategoriesSelectionDomainFactory(dependencies)
		}
		
		@Provides
		@MainActivityScope
		fun provideCategoriesSelectionDomainApi(
			categoriesSelectionDomainFactory: CategoriesSelectionDomainFactory
		): CategoriesSelectionDomainApi {
			return categoriesSelectionDomainFactory.create()
		}
		
		@Provides
		fun provideCategoriesSelectionStore(
			categoriesSelectionDomainApi: CategoriesSelectionDomainApi
		): CategoriesSelectionStore {
			return categoriesSelectionDomainApi.getStore()
		}
	}
}