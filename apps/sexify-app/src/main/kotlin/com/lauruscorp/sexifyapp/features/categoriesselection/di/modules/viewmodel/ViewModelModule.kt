package com.lauruscorp.sexifyapp.features.categoriesselection.di.modules.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.lauruscorp.core.di.dagger.mapkeys.ViewModelMapKey
import com.lauruscorp.sexifyapp.features.categoriesselection.viewmodel.CategoriesSelectionViewModel
import com.lauruscorp.sexifyapp.features.categoriesselection.viewmodel.CategoriesSelectionViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {
	@Binds
	@IntoMap
	@ViewModelMapKey(CategoriesSelectionViewModelImpl::class)
	abstract fun bindCategoriesSelectionViewModelIntoMap(
		categoriesSelectionViewModel: CategoriesSelectionViewModelImpl
	): ViewModel
	
	companion object {
		@Provides
		fun provideCategoriesSelectionViewModel(
			viewModelStoreOwner: ViewModelStoreOwner,
			viewModelFactory: ViewModelProvider.Factory
		): CategoriesSelectionViewModel {
			return ViewModelProvider(
				viewModelStoreOwner,
				viewModelFactory
			)[CategoriesSelectionViewModelImpl::class.java]
		}
	}
}