package com.lauruscorp.sexify_android.features.main.viewmodel

import androidx.lifecycle.LiveData
import com.lauruscorp.sexify_android.features.main.entities.UiError

internal interface MainViewModel {
	val errorEvent: LiveData<UiError>
}