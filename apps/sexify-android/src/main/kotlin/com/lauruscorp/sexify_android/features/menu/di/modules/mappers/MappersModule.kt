package com.lauruscorp.sexify_android.features.menu.di.modules.mappers

import com.lauruscorp.core_jvm.mapping.Mapper
import com.lauruscorp.features.menu_domain.store.MenuStore
import com.lauruscorp.sexify_android.features.menu.entities.UiError
import com.lauruscorp.sexify_android.features.menu.mappers.LabelToUiErrorMapper
import dagger.Binds
import dagger.Module

@Module
internal abstract class MappersModule {
    @Binds
    abstract fun provideLabelToUiErrorMapper(
        mapper: LabelToUiErrorMapper
    ): Mapper<MenuStore.Label, UiError>
}