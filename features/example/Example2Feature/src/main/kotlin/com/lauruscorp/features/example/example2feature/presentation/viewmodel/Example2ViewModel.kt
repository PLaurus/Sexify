package com.lauruscorp.features.example.example2feature.presentation.viewmodel

import androidx.lifecycle.LiveData
import com.lauruscorp.features.example.example2feature.presentation.entities.PresentationOperation
import com.lauruscorp.features.example.example2feature.presentation.entities.UiError

internal interface Example2ViewModel {
	val a: LiveData<String>
	val operation: LiveData<PresentationOperation>
	val b: LiveData<String>
	val result: LiveData<String>
	val errorEvent: LiveData<UiError>
	
	fun updateA(a: String)
	fun updateSelectedOperation(operation: PresentationOperation)
	fun updateB(b: String)
}