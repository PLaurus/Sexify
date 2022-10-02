package com.lauruscorp.sexify_android.features.menu.mappers

import com.lauruscorp.core_jvm.mapping.Mapper
import com.lauruscorp.features.menu_domain.store.MenuStore
import com.lauruscorp.sexify_android.features.menu.entities.UiError
import javax.inject.Inject

internal class LabelToUiErrorMapper @Inject constructor(
) : Mapper<MenuStore.Label, UiError> {
    override fun map(from: MenuStore.Label): UiError {
        return when (from) {
            is MenuStore.Label.ExceptionError -> UiError.System(exception = from.exception)
        }
    }
}