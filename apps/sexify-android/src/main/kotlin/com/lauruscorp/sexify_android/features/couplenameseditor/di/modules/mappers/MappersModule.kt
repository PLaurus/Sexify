package com.lauruscorp.sexify_android.features.couplenameseditor.di.modules.mappers

import com.lauruscorp.core_jvm.mapping.Mapper
import com.lauruscorp.features.couplenameseditordomain.store.CoupleNamesEditorStore
import com.lauruscorp.sexify_android.features.couplenameseditor.entities.UiError
import com.lauruscorp.sexify_android.features.couplenameseditor.mappers.LabelToUiErrorMapper
import dagger.Binds
import dagger.Module

@Module
internal abstract class MappersModule {
    @Binds
    abstract fun provideLabelToUiErrorMapper(
        mapper: LabelToUiErrorMapper
    ): @JvmSuppressWildcards Mapper<CoupleNamesEditorStore.Label, UiError>
}