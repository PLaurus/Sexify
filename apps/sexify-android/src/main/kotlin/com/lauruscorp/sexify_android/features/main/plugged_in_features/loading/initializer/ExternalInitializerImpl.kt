package com.lauruscorp.sexify_android.features.main.plugged_in_features.loading.initializer

import com.lauruscorp.sexify_android.application.di.modules.initialization.qualifiers.MainInitializerQualifier
import com.lauruscorp.sexify_android.application.initialization.Initializer
import com.lauruscorp.sexify_domain.loading.initializer.ExternalInitializer
import javax.inject.Inject

internal class ExternalInitializerImpl @Inject constructor(
    @MainInitializerQualifier private val initializer: Initializer
) : ExternalInitializer {
    override suspend fun initialize() {
        initializer.initialize()
    }
}