package com.lauruscorp.sexify_android.application.di.modules.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lauruscorp.core_android.android.viewmodel.DaggerViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
internal class ViewModelModule {
    @Provides
    fun provideViewModelFactory(
        viewModelProviders: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
    ): ViewModelProvider.Factory {
        return DaggerViewModelFactory(viewModelProviders)
    }
}