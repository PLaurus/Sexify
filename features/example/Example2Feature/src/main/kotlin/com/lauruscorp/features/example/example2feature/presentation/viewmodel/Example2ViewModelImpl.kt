package com.lauruscorp.features.example.example2feature.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.lauruscorp.core.android.livedata.EventLiveData
import com.lauruscorp.core.mapping.Mapper
import com.lauruscorp.exampledomain.entities.Operation
import com.lauruscorp.exampledomain.store.ExampleStore
import com.lauruscorp.features.example.example2feature.presentation.entities.PresentationOperation
import com.lauruscorp.features.example.example2feature.presentation.entities.UiError
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class Example2ViewModelImpl @Inject constructor(
	private val store: ExampleStore,
	private val operationToPresentationOperationMapper: @JvmSuppressWildcards Mapper<Operation, PresentationOperation>,
	private val presentationOperationToOperationMapper: @JvmSuppressWildcards Mapper<PresentationOperation, Operation>,
	private val labelToUiErrorMapper: @JvmSuppressWildcards Mapper<ExampleStore.Label, UiError>
) : ViewModel(), Example2ViewModel {
	override val a: LiveData<String> = MutableLiveData<String>().apply {
		viewModelScope.launch {
			store.states
				.map { state -> state.a?.toString() ?: "" }
				.distinctUntilChanged()
				.collect(::setValue)
		}
	}
	
	override val operation: LiveData<PresentationOperation> = MutableLiveData<PresentationOperation>().apply {
		viewModelScope.launch {
			store.states
				.map { it.operation }
				.map(operationToPresentationOperationMapper::map)
				.distinctUntilChanged()
				.collect(::setValue)
		}
	}
	
	override val b: LiveData<String> = MutableLiveData<String>().apply {
		viewModelScope.launch {
			store.states
				.map { state -> state.b?.toString() ?: "" }
				.distinctUntilChanged()
				.collect(::setValue)
		}
	}
	
	override val result: LiveData<String> = MutableLiveData<String>().apply {
		viewModelScope.launch {
			store.states
				.map { state -> state.result?.toString() ?: "" }
				.distinctUntilChanged()
				.collect(::setValue)
		}
	}
	override val errorEvent: LiveData<UiError> = EventLiveData<UiError>().apply {
		viewModelScope.launch {
			store.labels
				.map(labelToUiErrorMapper::map)
				.collect(::setValue)
		}
	}
	
	override fun updateA(a: String) {
		store.accept(
			ExampleStore.Intent.UpdateA(
				a = a.toIntOrNull()
			)
		)
	}
	
	override fun updateSelectedOperation(operation: PresentationOperation) {
		store.accept(
			ExampleStore.Intent.UpdateOperation(
				operation = presentationOperationToOperationMapper.map(operation)
			)
		)
	}
	
	override fun updateB(b: String) {
		store.accept(
			ExampleStore.Intent.UpdateB(
				b = b.toIntOrNull()
			)
		)
	}
}