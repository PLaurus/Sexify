package com.lauruscorp.sexifyapp.features.categoriesselection.presentation.viewmodel

import androidx.lifecycle.LiveData
import com.lauruscorp.sexifyapp.features.categoriesselection.presentation.entities.UiError

internal interface CategoriesSelectionViewModel {
	val errorEvent: LiveData<UiError>
}