package com.lauruscorp.sexify_android.features.categoriesselection.viewmodel

import androidx.lifecycle.LiveData
import com.lauruscorp.sexify_android.features.categoriesselection.entities.UiError

internal interface CategoriesSelectionViewModel {
	val errorEvent: LiveData<UiError>
}