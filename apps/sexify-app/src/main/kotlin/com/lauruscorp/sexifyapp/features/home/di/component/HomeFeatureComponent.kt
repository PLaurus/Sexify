package com.lauruscorp.sexifyapp.features.home.di.component

import androidx.lifecycle.ViewModelStoreOwner
import com.lauruscorp.features.homedomain.api.HomeDomainDependencies
import com.lauruscorp.sexifyapp.features.home.api.HomeFeatureDependencies
import com.lauruscorp.sexifyapp.features.home.di.component.scope.HomeFeatureScope
import com.lauruscorp.sexifyapp.features.home.di.modules.domain.DomainModule
import com.lauruscorp.sexifyapp.features.home.di.modules.mappers.MappersModule
import com.lauruscorp.sexifyapp.features.home.di.modules.ui.UiModule
import com.lauruscorp.sexifyapp.features.home.di.modules.viewmodel.ViewModelModule
import com.lauruscorp.sexifyapp.features.home.presentation.HomeFragment
import dagger.BindsInstance
import dagger.Component

@HomeFeatureScope
@Component(
	dependencies = [
		HomeFeatureDependencies::class
	],
	modules = [
		UiModule::class,
		ViewModelModule::class,
		MappersModule::class,
		DomainModule::class
	]
)
internal interface HomeFeatureComponent : HomeDomainDependencies {
	fun inject(fragment: HomeFragment)
	
	@Component.Factory
	interface Factory {
		fun create(
			dependencies: HomeFeatureDependencies,
			@BindsInstance viewModelStoreOwner: ViewModelStoreOwner
		): HomeFeatureComponent
	}
	
	companion object {
		operator fun invoke(
			dependencies: HomeFeatureDependencies,
			viewModelStoreOwner: ViewModelStoreOwner
		): HomeFeatureComponent {
			return DaggerHomeFeatureComponent.factory()
				.create(
					dependencies = dependencies,
					viewModelStoreOwner = viewModelStoreOwner
				)
		}
	}
}