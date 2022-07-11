package com.lauruscorp.sexifyapp.features.categoriesselection.mappers

import com.lauruscorp.core.mapping.Mapper
import com.lauruscorp.features.categoriesselectiondomain.store.CategoriesSelectionStore
import com.lauruscorp.sexifyapp.features.categoriesselection.entities.UiError
import javax.inject.Inject

internal class LabelToUiErrorMapper @Inject constructor(
) : Mapper<CategoriesSelectionStore.Label, UiError> {
	override fun map(from: CategoriesSelectionStore.Label): UiError {
		return when (from) {
			is CategoriesSelectionStore.Label.ExceptionError -> UiError.System(exception = from.exception)
		}
	}
}