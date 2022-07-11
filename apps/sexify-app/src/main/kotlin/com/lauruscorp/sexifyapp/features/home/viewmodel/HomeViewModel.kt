package com.lauruscorp.sexifyapp.features.home.viewmodel

import androidx.lifecycle.LiveData
import com.lauruscorp.sexifyapp.features.home.entities.UiError

internal interface HomeViewModel {
	val errorEvent: LiveData<UiError>
}