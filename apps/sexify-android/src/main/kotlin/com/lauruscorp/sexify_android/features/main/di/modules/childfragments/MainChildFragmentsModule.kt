package com.lauruscorp.sexify_android.features.main.di.modules.childfragments

import androidx.fragment.app.FragmentFactory
import com.lauruscorp.core_android.android.fragment.FragmentBuilder
import com.lauruscorp.sexify_android.features.categoriesselection.api.CategoriesSelectionFragment
import com.lauruscorp.sexify_android.features.couplenameseditor.api.CoupleNamesEditorFragment
import com.lauruscorp.sexify_android.features.game.api.presentation.GameFragment
import com.lauruscorp.sexify_android.features.main.MainChildFragmentsFactory
import com.lauruscorp.sexify_android.features.main.di.component.MainActivityComponent
import com.lauruscorp.sexify_android.features.main.di.component.scope.MainActivityScope
import com.lauruscorp.sexify_android.features.menu.api.MenuFragment
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
                MenuFragment(
                    dependencies = component
                )
            }
        }
    }
}