package com.lauruscorp.sexify_android.features.menu.di.modules.ui

import android.view.View
import com.lauruscorp.core_android.ui.ViewBindingUi
import com.lauruscorp.sexify_android.databinding.LayoutMenuBinding
import com.lauruscorp.sexify_android.features.menu.ui.MenuUi
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class UiModule {
    @Binds
    abstract fun provideMenuUi(
        ui: MenuUi
    ): @JvmSuppressWildcards ViewBindingUi<LayoutMenuBinding>

    companion object {
        @Provides
        fun provideViewBindingProvider(): (view: View) -> LayoutMenuBinding {
            return LayoutMenuBinding::bind
        }
    }
}