package com.lauruscorp.sexify_android.features.couplenameseditor.viewmodel

import androidx.lifecycle.LiveData
import com.lauruscorp.sexify_android.features.couplenameseditor.entities.UiError

internal interface CoupleNamesEditorViewModel {
	val errorEvent: LiveData<UiError>
}