package com.lauruscorp.sexifyapp.features.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.lauruscorp.core_android.android.livedata.EventLiveData
import com.lauruscorp.core_jvm.mapping.Mapper
import com.lauruscorp.features.homedomain.store.HomeStore
import com.lauruscorp.sexifyapp.features.home.entities.UiError
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class HomeViewModelImpl @Inject constructor(
    private val store: HomeStore,
    private val labelToUiErrorMapper: Mapper<HomeStore.Label, UiError>
) : ViewModel(), HomeViewModel {
    override val errorEvent: LiveData<UiError> = EventLiveData<UiError>().apply {
        viewModelScope.launch {
            store.labels
                .map(labelToUiErrorMapper::map)
                .collect(::setValue)
        }
    }
}