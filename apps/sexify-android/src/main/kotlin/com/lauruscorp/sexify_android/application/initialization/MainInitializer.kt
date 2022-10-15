package com.lauruscorp.sexify_android.application.initialization

import com.lauruscorp.sexify_android.application.di.modules.initialization.qualifiers.TasksDatabaseInitializerQualifier
import javax.inject.Inject

internal class MainInitializer @Inject constructor(
    @TasksDatabaseInitializerQualifier private val tasksDatabaseInitializer: Initializer
) : Initializer {
    override val dependencies: List<Class<out Initializer>>
        get() = emptyList()

    override suspend fun initialize() {
        tasksDatabaseInitializer.initialize()
    }
}