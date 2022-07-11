package com.lauruscorp.sexifyapp.features.main.di.modules.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.lauruscorp.core.di.dagger.mapkeys.ViewModelMapKey
import com.lauruscorp.sexifyapp.features.main.viewmodel.MainViewModel
import com.lauruscorp.sexifyapp.features.main.viewmodel.MainViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
internal abstract class MainViewModelModule {
	@Binds
	@IntoMap
	@ViewModelMapKey(MainViewModelImpl::class)
	abstract fun bindMainViewModelIntoMap(
		mainViewModel: MainViewModelImpl
	): ViewModel
	
	companion object {
		@Provides
		fun provideMainViewModel(
			viewModelStoreOwner: ViewModelStoreOwner,
			viewModelFactory: ViewModelProvider.Factory
		): MainViewModel {
			return ViewModelProvider(
				viewModelStoreOwner,
				viewModelFactory
			)[MainViewModelImpl::class.java]
		}
	}
}