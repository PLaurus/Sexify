package com.lauruscorp.sexify_android.features.main.plugged_in_features.loading.di

import com.lauruscorp.core_android.android.fragment.FragmentBuilder
import com.lauruscorp.sexify_android.features.loading.dependencies.LoadingFeatureDependencies
import com.lauruscorp.sexify_android.features.loading.presentation.LoadingFragment
import com.lauruscorp.sexify_android.features.main.di.component.scope.MainActivityScope
import com.lauruscorp.sexify_android.features.main.plugged_in_features.loading.dependencies.LoadingFeatureDependenciesImpl
import com.lauruscorp.sexify_android.features.main.plugged_in_features.loading.initializer.ExternalInitializerImpl
import com.lauruscorp.sexify_domain.loading.initializer.ExternalInitializer
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet

@Module
internal abstract class LoadingFeatureModule {
    @Binds
    abstract fun provideLoadingFeatureDependencies(
        dependencies: LoadingFeatureDependenciesImpl
    ): LoadingFeatureDependencies

    @Binds
    abstract fun provideExternalInitializer(
        initializer: ExternalInitializerImpl
    ): ExternalInitializer

    companion object {
        @Provides
        @IntoSet
        @MainActivityScope
        fun provideLoadingFragmentBuilder(
            dependencies: LoadingFeatureDependencies
        ): FragmentBuilder<*> {
            return FragmentBuilder {
                LoadingFragment(
                    dependencies = dependencies
                )
            }
        }
    }
}