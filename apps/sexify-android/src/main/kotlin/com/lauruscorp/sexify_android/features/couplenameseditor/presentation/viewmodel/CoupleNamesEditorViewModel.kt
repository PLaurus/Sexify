package com.lauruscorp.sexify_android.features.couplenameseditor.presentation.viewmodel

import androidx.lifecycle.LiveData
import com.lauruscorp.sexify_android.features.couplenameseditor.presentation.entities.UiError

internal interface CoupleNamesEditorViewModel {
	val errorEvent: LiveData<UiError>
}