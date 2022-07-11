package com.lauruscorp.sexifyapp.features.couplenameseditor.viewmodel

import androidx.lifecycle.LiveData
import com.lauruscorp.sexifyapp.features.couplenameseditor.entities.UiError

internal interface CoupleNamesEditorViewModel {
	val errorEvent: LiveData<UiError>
}