package com.lauruscorp.sexify_android.features.main.di.modules.ui

import android.view.View
import com.lauruscorp.core.ui.ViewBindingUi
import com.lauruscorp.sexify_android.databinding.LayoutMainBinding
import com.lauruscorp.sexify_android.features.main.ui.MainUi
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class MainUiModule {
    @Binds
    abstract fun provideMainUi(
        ui: MainUi
    ): @JvmSuppressWildcards ViewBindingUi<LayoutMainBinding>

    companion object {
        @Provides
        fun provideViewBindingProvider(): (view: View) -> LayoutMainBinding {
            return LayoutMainBinding::bind
        }
    }
}