package com.lauruscorp.sexifyapp.features.categoriesselection.viewmodel

import androidx.lifecycle.LiveData
import com.lauruscorp.sexifyapp.features.categoriesselection.entities.UiError

internal interface CategoriesSelectionViewModel {
	val errorEvent: LiveData<UiError>
}