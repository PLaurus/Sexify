package com.lauruscorp.sexifyapp.features.main.di.modules.childfragments

import androidx.fragment.app.FragmentFactory
import com.lauruscorp.core.android.fragment.FragmentBuilder
import com.lauruscorp.sexifyapp.features.categoriesselection.api.CategoriesSelectionFragment
import com.lauruscorp.sexifyapp.features.couplenameseditor.api.CoupleNamesEditorFragment
import com.lauruscorp.sexifyapp.features.game.api.GameFragment
import com.lauruscorp.sexifyapp.features.home.api.HomeFragment
import com.lauruscorp.sexifyapp.features.main.MainChildFragmentsFactory
import com.lauruscorp.sexifyapp.features.main.di.component.MainActivityComponent
import com.lauruscorp.sexifyapp.features.main.di.component.scope.MainActivityScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet

@Module
internal abstract class MainChildFragmentsModule {
	@Binds
	abstract fun provideFragmentFactory(
		fragmentFactory: MainChildFragmentsFactory
	): FragmentFactory
	
	companion object {
		@Provides
		@IntoSet
		@MainActivityScope
		fun provideCategoriesSelectionFragmentBuilder(
			component: MainActivityComponent
		): FragmentBuilder<*> {
			return FragmentBuilder {
				CategoriesSelectionFragment(
					dependencies = component
				)
			}
		}
		
		@Provides
		@IntoSet
		@MainActivityScope
		fun provideCoupleNamesEditorFragmentBuilder(
			component: MainActivityComponent
		): FragmentBuilder<*> {
			return FragmentBuilder {
				CoupleNamesEditorFragment(
					dependencies = component
				)
			}
		}
		
		@Provides
		@IntoSet
		@MainActivityScope
		fun provideGameFragmentBuilder(
			component: MainActivityComponent
		): FragmentBuilder<*> {
			return FragmentBuilder {
				GameFragment(
					dependencies = component
				)
			}
		}
		
		@Provides
		@IntoSet
		@MainActivityScope
		fun provideHomeFragmentBuilder(
			component: MainActivityComponent
		): FragmentBuilder<*> {
			return FragmentBuilder {
				HomeFragment(
					dependencies = component
				)
			}
		}
	}
}