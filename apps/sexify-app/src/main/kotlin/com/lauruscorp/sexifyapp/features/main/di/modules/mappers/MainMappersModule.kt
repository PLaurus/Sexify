package com.lauruscorp.sexifyapp.features.main.di.modules.mappers

import com.lauruscorp.core_jvm.mapping.Mapper
import com.lauruscorp.features.maindomain.store.MainStore
import com.lauruscorp.sexifyapp.features.main.entities.UiError
import com.lauruscorp.sexifyapp.features.main.mappers.LabelToUiErrorMapper
import dagger.Binds
import dagger.Module

@Module
internal interface MainMappersModule {
    @Binds
    fun provideLabelToUiErrorMapper(
        mapper: LabelToUiErrorMapper
    ): Mapper<MainStore.Label, UiError>
}