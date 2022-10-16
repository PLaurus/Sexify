package com.lauruscorp.sexify_android.features.couplenameseditor.di.modules.mappers

import com.lauruscorp.core.mapping.Mapper
import com.lauruscorp.features.couplenameseditordomain.store.CoupleNamesEditorStore
import com.lauruscorp.sexify_android.features.couplenameseditor.presentation.entities.UiError
import com.lauruscorp.sexify_android.features.couplenameseditor.presentation.mappers.LabelToUiErrorMapper
import dagger.Binds
import dagger.Module

@Module
internal abstract class MappersModule {
    @Binds
    abstract fun provideLabelToUiErrorMapper(
        mapper: LabelToUiErrorMapper
    ): @JvmSuppressWildcards Mapper<CoupleNamesEditorStore.Label, UiError>
}