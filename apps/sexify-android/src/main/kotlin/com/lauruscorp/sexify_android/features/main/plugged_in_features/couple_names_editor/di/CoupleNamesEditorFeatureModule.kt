package com.lauruscorp.sexify_android.features.main.plugged_in_features.couple_names_editor.di

import com.lauruscorp.core.android.fragment.FragmentBuilder
import com.lauruscorp.features.couplenameseditordomain.dependencies.data.PlayersProvider
import com.lauruscorp.sexify_android.features.couplenameseditor.dependencies.CoupleNamesEditorFeatureDependencies
import com.lauruscorp.sexify_android.features.couplenameseditor.presentation.CoupleNamesEditorFragment
import com.lauruscorp.sexify_android.features.main.di.component.scope.MainActivityScope
import com.lauruscorp.sexify_android.features.main.plugged_in_features.couple_names_editor.data.provider.CoupleNamesEditorFeaturePlayersProvider
import com.lauruscorp.sexify_android.features.main.plugged_in_features.couple_names_editor.dependencies.CoupleNamesEditorFeatureDependenciesImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet

@Module
internal abstract class CoupleNamesEditorFeatureModule {
    @Binds
    abstract fun provideCoupleNamesEditorFeatureDependencies(
        dependencies: CoupleNamesEditorFeatureDependenciesImpl
    ): CoupleNamesEditorFeatureDependencies

    @Binds
    abstract fun providePlayersProvider(
        provider: CoupleNamesEditorFeaturePlayersProvider
    ): PlayersProvider

    companion object {
        @Provides
        @IntoSet
        @MainActivityScope
        fun provideCoupleNamesEditorFragmentBuilder(
            dependencies: CoupleNamesEditorFeatureDependencies
        ): FragmentBuilder<*> {
            return FragmentBuilder {
                CoupleNamesEditorFragment(
                    dependencies = dependencies
                )
            }
        }
    }
}