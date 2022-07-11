package com.lauruscorp.sexifyapp.features.home.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.lauruscorp.core.android.livedata.EventLiveData
import com.lauruscorp.core.mapping.Mapper
import com.lauruscorp.features.homedomain.store.HomeStore
import com.lauruscorp.sexifyapp.features.home.presentation.entities.UiError
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class HomeViewModelImpl @Inject constructor(
	private val store: HomeStore,
	private val labelToUiErrorMapper: @JvmSuppressWildcards Mapper<HomeStore.Label, UiError>
) : ViewModel(), HomeViewModel {
	override val errorEvent: LiveData<UiError> = EventLiveData<UiError>().apply {
		viewModelScope.launch {
			store.labels
				.map(labelToUiErrorMapper::map)
				.collect(::setValue)
		}
	}
}