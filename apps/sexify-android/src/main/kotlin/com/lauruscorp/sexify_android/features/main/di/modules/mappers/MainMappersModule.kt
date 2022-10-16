package com.lauruscorp.sexify_android.features.main.di.modules.mappers

import com.lauruscorp.core.mapping.Mapper
import com.lauruscorp.features.maindomain.store.MainStore
import com.lauruscorp.sexify_android.features.main.entities.UiError
import com.lauruscorp.sexify_android.features.main.mappers.LabelToUiErrorMapper
import dagger.Binds
import dagger.Module

@Module
internal interface MainMappersModule {
    @Binds
    fun provideLabelToUiErrorMapper(
        mapper: LabelToUiErrorMapper
    ): Mapper<MainStore.Label, UiError>
}