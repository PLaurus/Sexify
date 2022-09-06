package com.lauruscorp.sexifyapp.features.game.di.modules.ui

import android.view.View
import com.lauruscorp.core_android.ui.ViewBindingUi
import com.lauruscorp.sexifyapp.databinding.LayoutGameBinding
import com.lauruscorp.sexifyapp.features.game.ui.GameUi
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class UiModule {
    @Binds
    abstract fun provideGameUi(
        ui: GameUi
    ): @JvmSuppressWildcards ViewBindingUi<LayoutGameBinding>

    companion object {
        @Provides
        fun provideViewBindingProvider(): (view: View) -> LayoutGameBinding {
            return LayoutGameBinding::bind
        }
    }
}