package com.lauruscorp.sexifyapp.features.game.di.modules.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.lauruscorp.core.di.dagger.mapkeys.ViewModelMapKey
import com.lauruscorp.sexifyapp.features.game.presentation.viewmodel.GameViewModel
import com.lauruscorp.sexifyapp.features.game.presentation.viewmodel.GameViewModelImpl
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