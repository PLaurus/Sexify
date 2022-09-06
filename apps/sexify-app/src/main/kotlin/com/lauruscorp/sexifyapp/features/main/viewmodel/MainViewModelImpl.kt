package com.lauruscorp.sexifyapp.features.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.lauruscorp.core_android.android.livedata.EventLiveData
import com.lauruscorp.core_jvm.mapping.Mapper
import com.lauruscorp.features.maindomain.store.MainStore
import com.lauruscorp.sexifyapp.features.main.entities.UiError
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class MainViewModelImpl @Inject constructor(
    private val store: MainStore,
    private val labelToUiErrorMapper: @JvmSuppressWildcards Mapper<MainStore.Label, UiError>
) : ViewModel(), MainViewModel {
    override val errorEvent: LiveData<UiError> = EventLiveData<UiError>().apply {
        viewModelScope.launch {
            store.labels
                .map(labelToUiErrorMapper::map)
                .collect(::setValue)
        }
    }
}