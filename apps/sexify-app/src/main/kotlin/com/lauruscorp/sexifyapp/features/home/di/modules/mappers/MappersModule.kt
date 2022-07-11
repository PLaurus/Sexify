package com.lauruscorp.sexifyapp.features.home.di.modules.mappers

import com.lauruscorp.core.mapping.Mapper
import com.lauruscorp.features.homedomain.store.HomeStore
import com.lauruscorp.sexifyapp.features.home.presentation.entities.UiError
import com.lauruscorp.sexifyapp.features.home.presentation.mappers.LabelToUiErrorMapper
import dagger.Binds
import dagger.Module

@Module
internal abstract class MappersModule {
	@Binds
	abstract fun provideLabelToUiErrorMapper(
		mapper: LabelToUiErrorMapper
	): Mapper<HomeStore.Label, UiError>
}