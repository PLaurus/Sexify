package com.lauruscorp.sexifyapp.features.main.presentation.viewmodel

import androidx.lifecycle.LiveData
import com.lauruscorp.sexifyapp.features.main.presentation.entities.UiError

internal interface MainViewModel {
	val errorEvent: LiveData<UiError>
}