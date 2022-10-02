package com.lauruscorp.sexify_android.features.couplenameseditor.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.lauruscorp.core_android.android.livedata.EventLiveData
import com.lauruscorp.core_jvm.mapping.Mapper
import com.lauruscorp.features.couplenameseditordomain.store.CoupleNamesEditorStore
import com.lauruscorp.sexify_android.features.couplenameseditor.entities.UiError
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class CoupleNamesEditorViewModelImpl @Inject constructor(
    private val store: CoupleNamesEditorStore,
    private val labelToUiErrorMapper: Mapper<CoupleNamesEditorStore.Label, UiError>
) : ViewModel(), CoupleNamesEditorViewModel {
    override val errorEvent: LiveData<UiError> = EventLiveData<UiError>().apply {
        viewModelScope.launch {
            store.labels
                .map(labelToUiErrorMapper::map)
                .collect(::setValue)
        }
    }
}