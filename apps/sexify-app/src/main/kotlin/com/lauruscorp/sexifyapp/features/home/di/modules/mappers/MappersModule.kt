package com.lauruscorp.sexifyapp.features.home.di.modules.mappers

import com.lauruscorp.core_jvm.mapping.Mapper
import com.lauruscorp.features.homedomain.store.HomeStore
import com.lauruscorp.sexifyapp.features.home.entities.UiError
import com.lauruscorp.sexifyapp.features.home.mappers.LabelToUiErrorMapper
import dagger.Binds
import dagger.Module

@Module
internal abstract class MappersModule {
    @Binds
    abstract fun provideLabelToUiErrorMapper(
        mapper: LabelToUiErrorMapper
    ): @JvmSuppressWildcards Mapper<HomeStore.Label, UiError>
}