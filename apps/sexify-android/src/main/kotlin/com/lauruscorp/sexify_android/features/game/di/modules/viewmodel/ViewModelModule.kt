package com.lauruscorp.sexify_android.features.game.di.modules.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.lauruscorp.core_android.di.dagger.mapkeys.ViewModelMapKey
import com.lauruscorp.sexify_android.features.game.viewmodel.GameViewModel
import com.lauruscorp.sexify_android.features.game.viewmodel.GameViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelMapKey(GameViewModelImpl::class)
    abstract fun bindGameViewModelIntoMap(
        gameViewModel: GameViewModelImpl
    ): ViewModel

    companion object {
        @Provides
        fun provideGameViewModel(
            viewModelStoreOwner: ViewModelStoreOwner,
            viewModelFactory: ViewModelProvider.Factory
        ): GameViewModel {
            return ViewModelProvider(
                viewModelStoreOwner,
                viewModelFactory
            )[GameViewModelImpl::class.java]
        }
    }
}