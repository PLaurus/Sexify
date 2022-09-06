package com.lauruscorp.features.example.example2feature.presentation.di.modules.ui

import android.view.View
import com.lauruscorp.core_android.ui.ViewBindingUi
import com.lauruscorp.features.example.example2feature.databinding.LayoutExample2Binding
import com.lauruscorp.features.example.example2feature.presentation.ui.Example2Ui
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class UiModule {
    @Binds
    abstract fun provideExample2Ui(
        ui: Example2Ui
    ): ViewBindingUi<LayoutExample2Binding>

    companion object {
        @Provides
        fun provideViewBindingProvider(): (view: View) -> LayoutExample2Binding {
            return LayoutExample2Binding::bind
        }
    }
}