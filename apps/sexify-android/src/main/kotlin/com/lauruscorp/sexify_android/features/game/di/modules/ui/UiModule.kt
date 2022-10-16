package com.lauruscorp.sexify_android.features.game.di.modules.ui

import android.view.View
import com.lauruscorp.core.ui.ViewBindingUi
import com.lauruscorp.sexify_android.databinding.LayoutGameBinding
import com.lauruscorp.sexify_android.features.game.ui.GameUi
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