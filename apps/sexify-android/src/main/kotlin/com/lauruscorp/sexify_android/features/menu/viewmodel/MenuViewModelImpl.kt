package com.lauruscorp.sexify_android.features.menu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.lauruscorp.core.android.livedata.EventLiveData
import com.lauruscorp.core.mapping.Mapper
import com.lauruscorp.features.menu_domain.store.MenuStore
import com.lauruscorp.sexify_android.features.menu.entities.UiError
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class MenuViewModelImpl @Inject constructor(
    private val store: MenuStore,
    private val labelToUiErrorMapper: @JvmSuppressWildcards Mapper<MenuStore.Label, UiError>
) : ViewModel(), MenuViewModel {
    override val errorEvent: LiveData<UiError> = EventLiveData<UiError>().apply {
        viewModelScope.launch {
            store.labels
                .map(labelToUiErrorMapper::map)
                .collect(::setValue)
        }
    }
    
    private val _startGameClickedEvent = EventLiveData<Unit>()
    override val startGameClickedEvent: LiveData<Unit> = _startGameClickedEvent
    
    override fun onStartGameClicked() {
        _startGameClickedEvent.value = Unit
    }
}