package com.lauruscorp.sexifyapp.features.categoriesselection.di.component

import androidx.lifecycle.ViewModelStoreOwner
import com.lauruscorp.features.categoriesselectiondomain.api.CategoriesSelectionDomainDependencies
import com.lauruscorp.sexifyapp.features.categoriesselection.api.CategoriesSelectionFeatureDependencies
import com.lauruscorp.sexifyapp.features.categoriesselection.di.component.scope.CategoriesSelectionFeatureScope
import com.lauruscorp.sexifyapp.features.categoriesselection.di.modules.domain.DomainModule
import com.lauruscorp.sexifyapp.features.categoriesselection.di.modules.mappers.MappersModule
import com.lauruscorp.sexifyapp.features.categoriesselection.di.modules.ui.UiModule
import com.lauruscorp.sexifyapp.features.categoriesselection.di.modules.viewmodel.ViewModelModule
import com.lauruscorp.sexifyapp.features.categoriesselection.presentation.CategoriesSelectionFragment
import dagger.BindsInstance
import dagger.Component

@CategoriesSelectionFeatureScope
@Component(
	dependencies = [
		CategoriesSelectionFeatureDependencies::class
	],
	modules = [
		UiModule::class,
		ViewModelModule::class,
		MappersModule::class,
		DomainModule::class
	]
)
internal interface CategoriesSelectionFeatureComponent : CategoriesSelectionDomainDependencies {
	fun inject(fragment: CategoriesSelectionFragment)
	
	@Component.Factory
	interface Factory {
		fun create(
			dependencies: CategoriesSelectionFeatureDependencies,
			@BindsInstance viewModelStoreOwner: ViewModelStoreOwner
		): CategoriesSelectionFeatureComponent
	}
	
	companion object {
		operator fun invoke(
			dependencies: CategoriesSelectionFeatureDependencies,
			viewModelStoreOwner: ViewModelStoreOwner
		): CategoriesSelectionFeatureComponent {
			return DaggerCategoriesSelectionFeatureComponent.factory()
				.create(
					dependencies = dependencies,
					viewModelStoreOwner = viewModelStoreOwner
				)
		}
	}
}