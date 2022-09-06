package com.lauruscorp.exampleapp.application.di.modules.application

import android.app.Application
import android.content.Context
import com.lauruscorp.core_android.di.dagger.qualifiers.context.ApplicationContextQualifier
import dagger.Module
import dagger.Provides

@Module
internal class ApplicationModule {
    @Provides
    @ApplicationContextQualifier
    fun provideApplicationContext(
        application: Application
    ): Context {
        return application
    }
}