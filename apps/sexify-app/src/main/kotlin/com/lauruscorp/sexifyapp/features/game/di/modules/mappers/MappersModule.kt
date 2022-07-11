package com.lauruscorp.sexifyapp.features.game.di.modules.mappers

import com.lauruscorp.core.mapping.Mapper
import com.lauruscorp.features.gamedomain.store.GameStore
import com.lauruscorp.sexifyapp.features.game.presentation.entities.UiError
import com.lauruscorp.sexifyapp.features.game.presentation.mappers.LabelToUiErrorMapper
import dagger.Binds
import dagger.Module

@Module
internal abstract class MappersModule {
	@Binds
	abstract fun provideLabelToUiErrorMapper(
		mapper: LabelToUiErrorMapper
	): Mapper<GameStore.Label, UiError>
}