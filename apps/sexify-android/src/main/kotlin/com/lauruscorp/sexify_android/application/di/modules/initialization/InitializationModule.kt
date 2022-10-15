package com.lauruscorp.sexify_android.application.di.modules.initialization

import com.lauruscorp.sexify_android.application.di.modules.initialization.qualifiers.MainInitializerQualifier
import com.lauruscorp.sexify_android.application.di.modules.initialization.qualifiers.TasksDatabaseInitializerQualifier
import com.lauruscorp.sexify_android.application.initialization.Initializer
import com.lauruscorp.sexify_android.application.initialization.MainInitializer
import com.lauruscorp.sexify_android.application.initialization.TasksDatabaseInitializer
import dagger.Binds
import dagger.Module

@Module
internal interface InitializationModule {
    @Binds
    @MainInitializerQualifier
    fun provideMainInitializer(
        initializer: MainInitializer
    ): Initializer

    @Binds
    @TasksDatabaseInitializerQualifier
    fun provideTasksDatabaseInitializer(
        initializer: TasksDatabaseInitializer
    ): Initializer
}