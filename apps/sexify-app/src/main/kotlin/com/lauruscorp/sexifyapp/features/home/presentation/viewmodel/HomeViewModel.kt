package com.lauruscorp.sexifyapp.features.home.presentation.viewmodel

import androidx.lifecycle.LiveData
import com.lauruscorp.sexifyapp.features.home.presentation.entities.UiError

internal interface HomeViewModel {
	val errorEvent: LiveData<UiError>
}