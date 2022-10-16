package com.lauruscorp.sexify_android.features.loading.di.modules.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.lauruscorp.core.android.viewmodel.DaggerViewModelFactory
import com.lauruscorp.core.di.dagger.mapkeys.ViewModelMapKey
import com.lauruscorp.sexify_android.features.loading.presentation.viewmodel.LoadingViewModel
import com.lauruscorp.sexify_android.features.loading.presentation.viewmodel.LoadingViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider

@Module
internal abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelMapKey(LoadingViewModelImpl::class)
    abstract fun bindLoadingViewModelIntoMap(
        loadingViewModel: LoadingViewModelImpl
    ): ViewModel

    companion object {
        @Provides
        fun provideViewModelFactory(
            viewModelProviders: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
        ): ViewModelProvider.Factory {
            return DaggerViewModelFactory(viewModelProviders)
        }

        @Provides
        fun provideLoadingViewModel(
            viewModelStoreOwner: ViewModelStoreOwner,
            viewModelFactory: ViewModelProvider.Factory
        ): LoadingViewModel {
            return ViewModelProvider(
                viewModelStoreOwner,
                viewModelFactory
            )[LoadingViewModelImpl::class.java]
        }
    }
}