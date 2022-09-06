package com.lauruscorp.features.example.example2feature.presentation.di.modules.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.lauruscorp.core_android.android.viewmodel.DaggerViewModelFactory
import com.lauruscorp.core_android.di.dagger.mapkeys.ViewModelMapKey
import com.lauruscorp.features.example.example2feature.presentation.viewmodel.Example2ViewModel
import com.lauruscorp.features.example.example2feature.presentation.viewmodel.Example2ViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider

@Module
internal abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelMapKey(Example2ViewModelImpl::class)
    abstract fun bindExampleViewModelIntoMap(
        exampleViewModel: Example2ViewModelImpl
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
        ): Example2ViewModel {
            return ViewModelProvider(
                viewModelStoreOwner,
                viewModelFactory
            )[Example2ViewModelImpl::class.java]
        }
    }
}