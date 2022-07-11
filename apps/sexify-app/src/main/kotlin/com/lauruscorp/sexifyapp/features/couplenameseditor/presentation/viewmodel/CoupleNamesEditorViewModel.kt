package com.lauruscorp.sexifyapp.features.couplenameseditor.presentation.viewmodel

import androidx.lifecycle.LiveData
import com.lauruscorp.sexifyapp.features.couplenameseditor.presentation.entities.UiError

internal interface CoupleNamesEditorViewModel {
	val errorEvent: LiveData<UiError>
}