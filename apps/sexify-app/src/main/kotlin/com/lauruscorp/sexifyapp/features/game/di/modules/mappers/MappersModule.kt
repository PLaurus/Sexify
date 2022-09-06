package com.lauruscorp.sexifyapp.features.game.di.modules.mappers

import com.lauruscorp.core_jvm.mapping.Mapper
import com.lauruscorp.features.gamedomain.store.GameStore
import com.lauruscorp.sexifyapp.features.game.entities.UiError
import com.lauruscorp.sexifyapp.features.game.mappers.LabelToUiErrorMapper
import dagger.Binds
import dagger.Module

@Module
internal abstract class MappersModule {
    @Binds
    abstract fun provideLabelToUiErrorMapper(
        mapper: LabelToUiErrorMapper
    ): @JvmSuppressWildcards Mapper<GameStore.Label, UiError>
}