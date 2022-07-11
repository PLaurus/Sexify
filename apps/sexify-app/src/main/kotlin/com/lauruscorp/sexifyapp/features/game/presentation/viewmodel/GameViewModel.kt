package com.lauruscorp.sexifyapp.features.game.presentation.viewmodel

import androidx.lifecycle.LiveData
import com.lauruscorp.sexifyapp.features.game.presentation.entities.UiError

internal interface GameViewModel {
	val errorEvent: LiveData<UiError>
}