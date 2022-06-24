package com.lauruscorp.features.example.examplefeature.presentation.di.modules.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.lauruscorp.core.android.viewmodel.DaggerViewModelFactory
import com.lauruscorp.core.di.dagger.mapkeys.ViewModelMapKey
import com.lauruscorp.features.example.examplefeature.presentation.viewmodel.ExampleViewModel
import com.lauruscorp.features.example.examplefeature.presentation.viewmodel.ExampleViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider

@Module
internal abstract class ViewModelModule {
	@Binds
	@IntoMap
	@ViewModelMapKey(ExampleViewModelImpl::class)
	abstract fun bindExampleViewModelIntoMap(
		exampleViewModel: ExampleViewModelImpl
	): ViewModel
	
	companion object {
		@Provides
		fun provideViewModelFactory(
			viewModelProviders: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
		): ViewModelProvider.Factory {
			return DaggerViewModelFactory(viewModelProviders)
		}
		
		@Provides
		fun provideExampleViewModel(
			viewModelStoreOwner: ViewModelStoreOwner,
			viewModelFactory: ViewModelProvider.Factory
		): ExampleViewModel {
			return ViewModelProvider(
				viewModelStoreOwner,
				viewModelFactory
			)[ExampleViewModelImpl::class.java]
		}
	}
}