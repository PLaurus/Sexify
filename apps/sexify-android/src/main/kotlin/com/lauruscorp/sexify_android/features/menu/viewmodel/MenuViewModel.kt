package com.lauruscorp.sexify_android.features.menu.viewmodel

import androidx.lifecycle.LiveData
import com.lauruscorp.sexify_android.features.menu.entities.UiError

internal interface MenuViewModel {
	val errorEvent: LiveData<UiError>
	val startGameClickedEvent: LiveData<Unit>
	fun onStartGameClicked()
}