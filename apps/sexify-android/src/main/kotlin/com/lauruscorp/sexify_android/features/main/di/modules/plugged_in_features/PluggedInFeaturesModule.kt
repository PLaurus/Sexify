package com.lauruscorp.sexify_android.features.main.di.modules.plugged_in_features

import androidx.fragment.app.FragmentFactory
import com.lauruscorp.sexify_android.features.main.plugged_in_features.PluggedInFeatureFragmentFactory
import com.lauruscorp.sexify_android.features.main.plugged_in_features.loading.di.LoadingFeatureModule
import com.lauruscorp.sexify_android.features.main.plugged_in_features.menu.di.MenuFeatureModule
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        LoadingFeatureModule::class,
        MenuFeatureModule::class
    ]
)
internal interface PluggedInFeaturesModule {
    @Binds
    fun providePluggedInFeatureFragmentFactory(
        fragmentFactory: PluggedInFeatureFragmentFactory
    ): FragmentFactory
}