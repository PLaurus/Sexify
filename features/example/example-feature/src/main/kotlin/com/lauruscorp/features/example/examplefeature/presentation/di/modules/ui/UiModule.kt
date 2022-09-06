package com.lauruscorp.features.example.examplefeature.presentation.di.modules.ui

import android.view.View
import com.lauruscorp.core_android.ui.ViewBindingUi
import com.lauruscorp.features.example.examplefeature.databinding.LayoutExampleBinding
import com.lauruscorp.features.example.examplefeature.presentation.ui.ExampleUi
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class UiModule {
    @Binds
    abstract fun provideExampleUi(
        ui: ExampleUi
    ): ViewBindingUi<LayoutExampleBinding>

    companion object {
        @Provides
        fun provideViewBindingProvider(): (View) -> LayoutExampleBinding {
            return LayoutExampleBinding::bind
        }
    }
}