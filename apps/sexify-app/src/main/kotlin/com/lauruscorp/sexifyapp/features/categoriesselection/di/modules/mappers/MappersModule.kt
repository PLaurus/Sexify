package com.lauruscorp.sexifyapp.features.categoriesselection.di.modules.mappers

import com.lauruscorp.core.mapping.Mapper
import com.lauruscorp.features.categoriesselectiondomain.store.CategoriesSelectionStore
import com.lauruscorp.sexifyapp.features.categoriesselection.presentation.entities.UiError
import com.lauruscorp.sexifyapp.features.categoriesselection.presentation.mappers.LabelToUiErrorMapper
import dagger.Binds
import dagger.Module

@Module
internal abstract class MappersModule {
	@Binds
	abstract fun provideLabelToUiErrorMapper(
		mapper: LabelToUiErrorMapper
	): Mapper<CategoriesSelectionStore.Label, UiError>
}