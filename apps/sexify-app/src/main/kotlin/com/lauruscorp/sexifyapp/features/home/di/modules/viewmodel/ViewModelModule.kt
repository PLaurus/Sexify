package com.lauruscorp.sexifyapp.features.home.di.modules.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.lauruscorp.core.di.dagger.mapkeys.ViewModelMapKey
import com.lauruscorp.sexifyapp.features.home.viewmodel.HomeViewModel
import com.lauruscorp.sexifyapp.features.home.viewmodel.HomeViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {
	@Binds
	@IntoMap
	@ViewModelMapKey(HomeViewModelImpl::class)
	abstract fun bindHomeViewModelIntoMap(
		homeViewModel: HomeViewModelImpl
	): ViewModel
	
	companion object {
		@Provides
		fun provideHomeViewModel(
			viewModelStoreOwner: ViewModelStoreOwner,
			viewModelFactory: ViewModelProvider.Factory
		): HomeViewModel {
			return ViewModelProvider(
				viewModelStoreOwner,
				viewModelFactory
			)[HomeViewModelImpl::class.java]
		}
	}
}