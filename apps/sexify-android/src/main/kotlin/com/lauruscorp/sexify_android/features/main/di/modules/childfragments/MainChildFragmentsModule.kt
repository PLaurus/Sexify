package com.lauruscorp.sexify_android.features.main.di.modules.childfragments

import com.lauruscorp.core_android.android.fragment.FragmentBuilder
import com.lauruscorp.sexify_android.features.categoriesselection.api.CategoriesSelectionFragment
import com.lauruscorp.sexify_android.features.couplenameseditor.presentation.CoupleNamesEditorFragment
import com.lauruscorp.sexify_android.features.game.api.presentation.GameFragment
import com.lauruscorp.sexify_android.features.main.di.component.MainActivityComponent
import com.lauruscorp.sexify_android.features.main.di.component.scope.MainActivityScope
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet

@Module
internal abstract class MainChildFragmentsModule {
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
    }
}