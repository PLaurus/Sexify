package com.lauruscorp.sexify_android.features.categoriesselection.di.modules.ui

import android.view.View
import com.lauruscorp.core_android.ui.ViewBindingUi
import com.lauruscorp.sexify_android.databinding.LayoutCategoriesSelectionBinding
import com.lauruscorp.sexify_android.features.categoriesselection.ui.CategoriesSelectionUi
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