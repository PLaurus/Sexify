package com.lauruscorp.sexifyapp.features.main.di.modules.mappers

import com.lauruscorp.core.mapping.Mapper
import com.lauruscorp.features.maindomain.store.MainStore
import com.lauruscorp.sexifyapp.features.main.presentation.entities.UiError
import com.lauruscorp.sexifyapp.features.main.presentation.mappers.LabelToUiErrorMapper
import dagger.Binds
import dagger.Module

@Module
internal abstract class MappersModule {
	@Binds
	abstract fun provideLabelToUiErrorMapper(
		mapper: LabelToUiErrorMapper
	): Mapper<MainStore.Label, UiError>
}