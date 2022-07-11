package com.lauruscorp.sexifyapp.features.game.viewmodel

import androidx.lifecycle.LiveData
import com.lauruscorp.sexifyapp.features.game.entities.UiError

internal interface GameViewModel {
	val errorEvent: LiveData<UiError>
}