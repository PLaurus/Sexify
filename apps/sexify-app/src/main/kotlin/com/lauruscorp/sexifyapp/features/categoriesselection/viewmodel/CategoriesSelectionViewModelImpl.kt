package com.lauruscorp.sexifyapp.features.categoriesselection.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.lauruscorp.core_android.android.livedata.EventLiveData
import com.lauruscorp.core_jvm.mapping.Mapper
import com.lauruscorp.features.categoriesselectiondomain.store.CategoriesSelectionStore
import com.lauruscorp.sexifyapp.features.categoriesselection.entities.UiError
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class CategoriesSelectionViewModelImpl @Inject constructor(
    private val store: CategoriesSelectionStore,
    private val labelToUiErrorMapper: Mapper<CategoriesSelectionStore.Label, UiError>
) : ViewModel(), CategoriesSelectionViewModel {
    override val errorEvent: LiveData<UiError> = EventLiveData<UiError>().apply {
        viewModelScope.launch {
            store.labels
                .map(labelToUiErrorMapper::map)
                .collect(::setValue)
        }
    }
}