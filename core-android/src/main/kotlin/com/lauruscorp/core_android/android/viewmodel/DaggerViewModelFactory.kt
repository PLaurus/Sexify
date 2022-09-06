package com.lauruscorp.core_android.android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Provider

class DaggerViewModelFactory(
    private val viewModelProviders: Map<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModelProvider = requireNotNull(viewModelProviders[modelClass])
        return viewModelProvider.get() as T
    }
}