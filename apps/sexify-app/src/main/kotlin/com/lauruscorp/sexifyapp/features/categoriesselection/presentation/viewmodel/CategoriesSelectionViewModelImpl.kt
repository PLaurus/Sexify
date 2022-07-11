package com.lauruscorp.sexifyapp.features.categoriesselection.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.lauruscorp.core.android.livedata.EventLiveData
import com.lauruscorp.core.mapping.Mapper
import com.lauruscorp.features.categoriesselectiondomain.store.CategoriesSelectionStore
import com.lauruscorp.sexifyapp.features.categoriesselection.presentation.entities.UiError
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class CategoriesSelectionViewModelImpl @Inject constructor(
	private val store: CategoriesSelectionStore,
	private val labelToUiErrorMapper: @JvmSuppressWildcards Mapper<CategoriesSelectionStore.Label, UiError>
) : ViewModel(), CategoriesSelectionViewModel {
	override val errorEvent: LiveData<UiError> = EventLiveData<UiError>().apply {
		viewModelScope.launch {
			store.labels
				.map(labelToUiErrorMapper::map)
				.collect(::setValue)
		}
	}
}