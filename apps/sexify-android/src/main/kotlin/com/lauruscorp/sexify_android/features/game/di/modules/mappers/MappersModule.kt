package com.lauruscorp.sexify_android.features.game.di.modules.mappers

import com.lauruscorp.core.mapping.Mapper
import com.lauruscorp.features.gamedomain.store.GameStore
import com.lauruscorp.sexify_android.features.game.entities.UiError
import com.lauruscorp.sexify_android.features.game.mappers.LabelToUiErrorMapper
import dagger.Binds
import dagger.Module

@Module
internal abstract class MappersModule {
    @Binds
    abstract fun provideLabelToUiErrorMapper(
        mapper: LabelToUiErrorMapper
    ): @JvmSuppressWildcards Mapper<GameStore.Label, UiError>
}