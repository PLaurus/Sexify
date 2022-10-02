package com.lauruscorp.sexify_android.features.menu.di.component

import androidx.lifecycle.ViewModelStoreOwner
import com.lauruscorp.features.menu_domain.api.MenuDomainDependencies
import com.lauruscorp.sexify_android.features.menu.api.MenuFeatureDependencies
import com.lauruscorp.sexify_android.features.menu.api.MenuFragment
import com.lauruscorp.sexify_android.features.menu.di.component.scope.MenuFeatureScope
import com.lauruscorp.sexify_android.features.menu.di.modules.domain.DomainModule
import com.lauruscorp.sexify_android.features.menu.di.modules.mappers.MappersModule
import com.lauruscorp.sexify_android.features.menu.di.modules.ui.UiModule
import com.lauruscorp.sexify_android.features.menu.di.modules.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component

@MenuFeatureScope
@Component(
	dependencies = [
		MenuFeatureDependencies::class
	],
	modules = [
		UiModule::class,
		ViewModelModule::class,
		MappersModule::class,
		DomainModule::class
	]
)
internal interface MenuFeatureComponent : MenuDomainDependencies {
	fun inject(fragment: MenuFragment)
	
	@Component.Factory
	interface Factory {
		fun create(
			dependencies: MenuFeatureDependencies,
			@BindsInstance viewModelStoreOwner: ViewModelStoreOwner
		): MenuFeatureComponent
	}
	
	companion object {
		operator fun invoke(
			dependencies: MenuFeatureDependencies,
			viewModelStoreOwner: ViewModelStoreOwner
		): MenuFeatureComponent {
			return DaggerMenuFeatureComponent.factory()
				.create(
					dependencies = dependencies,
					viewModelStoreOwner = viewModelStoreOwner
				)
		}
	}
}