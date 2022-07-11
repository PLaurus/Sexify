package com.lauruscorp.sexifyapp.features.categoriesselection.di.modules.ui

import android.view.View
import com.lauruscorp.core.ui.ViewBindingUi
import com.lauruscorp.sexifyapp.databinding.LayoutCategoriesSelectionBinding
import com.lauruscorp.sexifyapp.features.categoriesselection.presentation.ui.CategoriesSelectionUi
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class UiModule {
	@Binds
	abstract fun provideCategoriesSelectionUi(
		ui: CategoriesSelectionUi
	): @JvmSuppressWildcards ViewBindingUi<LayoutCategoriesSelectionBinding>
	
	companion object {
		@Provides
		fun provideViewBindingProvider(): (view: View) -> LayoutCategoriesSelectionBinding {
			return LayoutCategoriesSelectionBinding::bind
		}
	}
}