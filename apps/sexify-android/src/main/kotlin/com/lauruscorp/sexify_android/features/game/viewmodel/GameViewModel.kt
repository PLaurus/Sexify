package com.lauruscorp.sexify_android.features.game.viewmodel

import androidx.lifecycle.LiveData
import com.lauruscorp.sexify_android.features.game.entities.UiError

internal interface GameViewModel {
	val errorEvent: LiveData<UiError>
}