package com.lauruscorp.sexify_android.features.menu.di.modules.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.lauruscorp.core_android.di.dagger.mapkeys.ViewModelMapKey
import com.lauruscorp.sexify_android.features.menu.viewmodel.MenuViewModel
import com.lauruscorp.sexify_android.features.menu.viewmodel.MenuViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelMapKey(MenuViewModelImpl::class)
    abstract fun bindMenuViewModelIntoMap(
        menuViewModel: MenuViewModelImpl
    ): ViewModel

    companion object {
        @Provides
        fun provideMenuViewModel(
            viewModelStoreOwner: ViewModelStoreOwner,
            viewModelFactory: ViewModelProvider.Factory
        ): MenuViewModel {
            return ViewModelProvider(
                viewModelStoreOwner,
                viewModelFactory
            )[MenuViewModelImpl::class.java]
        }
    }
}