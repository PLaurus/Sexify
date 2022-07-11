package com.lauruscorp.sexifyapp.features.couplenameseditor.di.modules.mappers

import com.lauruscorp.core.mapping.Mapper
import com.lauruscorp.features.couplenameseditordomain.store.CoupleNamesEditorStore
import com.lauruscorp.sexifyapp.features.couplenameseditor.entities.UiError
import com.lauruscorp.sexifyapp.features.couplenameseditor.mappers.LabelToUiErrorMapper
import dagger.Binds
import dagger.Module

@Module
internal abstract class MappersModule {
	@Binds
	abstract fun provideLabelToUiErrorMapper(
		mapper: LabelToUiErrorMapper
	): @JvmSuppressWildcards Mapper<CoupleNamesEditorStore.Label, UiError>
}