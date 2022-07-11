package com.lauruscorp.sexifyapp.features.main.viewmodel

import androidx.lifecycle.LiveData
import com.lauruscorp.sexifyapp.features.main.entities.UiError

internal interface MainViewModel {
	val errorEvent: LiveData<UiError>
}