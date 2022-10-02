package com.lauruscorp.sexify_android.features.categoriesselection.di.modules.mappers

import com.lauruscorp.core_jvm.mapping.Mapper
import com.lauruscorp.features.categoriesselectiondomain.store.CategoriesSelectionStore
import com.lauruscorp.sexify_android.features.categoriesselection.entities.UiError
import com.lauruscorp.sexify_android.features.categoriesselection.mappers.LabelToUiErrorMapper
import dagger.Binds
import dagger.Module

@Module
internal abstract class MappersModule {
    @Binds
    abstract fun provideLabelToUiErrorMapper(
        mapper: LabelToUiErrorMapper
    ): @JvmSuppressWildcards Mapper<CategoriesSelectionStore.Label, UiError>
}