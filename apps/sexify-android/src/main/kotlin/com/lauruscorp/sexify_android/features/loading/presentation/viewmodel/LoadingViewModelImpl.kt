package com.lauruscorp.sexify_android.features.loading.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.lauruscorp.sexify_domain.loading.entities.LoadingState
import com.lauruscorp.sexify_domain.loading.store.LoadingStore
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class LoadingViewModelImpl @Inject constructor(
    private val store: LoadingStore,
    initialStoreState: LoadingStore.State
) : ViewModel(), LoadingViewModel {
    private val _loadingState = MutableLiveData(initialStoreState.loadingState).apply {
        viewModelScope.launch {
            store.states
                .map { state -> state.loadingState }
                .collect(::setValue)
        }
    }
    override val loadingState: LiveData<LoadingState> = _loadingState
}