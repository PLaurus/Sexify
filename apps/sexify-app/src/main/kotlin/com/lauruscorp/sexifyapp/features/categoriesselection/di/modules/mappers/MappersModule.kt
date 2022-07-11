package com.lauruscorp.sexifyapp.features.categoriesselection.di.modules.mappers

import com.lauruscorp.core.mapping.Mapper
import com.lauruscorp.features.categoriesselectiondomain.store.CategoriesSelectionStore
import com.lauruscorp.sexifyapp.features.categoriesselection.entities.UiError
import com.lauruscorp.sexifyapp.features.categoriesselection.mappers.LabelToUiErrorMapper
import dagger.Binds
import dagger.Module

@Module
internal abstract class MappersModule {
	@Binds
	abstract fun provideLabelToUiErrorMapper(
		mapper: LabelToUiErrorMapper
	): @JvmSuppressWildcards Mapper<CategoriesSelectionStore.Label, UiError>
}