package com.lauruscorp.sexify_android.features.main.plugged_in_features.menu.di

import com.lauruscorp.core.android.fragment.FragmentBuilder
import com.lauruscorp.sexify_android.features.main.di.component.scope.MainActivityScope
import com.lauruscorp.sexify_android.features.main.plugged_in_features.menu.dependencies.MenuFeatureDependenciesImpl
import com.lauruscorp.sexify_android.features.menu.api.MenuFeatureDependencies
import com.lauruscorp.sexify_android.features.menu.api.MenuFragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet

@Module
internal abstract class MenuFeatureModule {
	@Binds
	abstract fun provideMenuFeatureDependencies(
		dependencies: MenuFeatureDependenciesImpl
	): MenuFeatureDependencies
	
	companion object {
		@Provides
		@IntoSet
		@MainActivityScope
		fun provideMenuFragmentBuilder(
			dependencies: MenuFeatureDependencies
		): FragmentBuilder<*> {
			return FragmentBuilder {
				MenuFragment(
					dependencies = dependencies
				)
			}
		}
	}
}