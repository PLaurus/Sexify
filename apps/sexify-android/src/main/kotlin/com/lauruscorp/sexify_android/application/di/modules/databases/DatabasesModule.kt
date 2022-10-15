package com.lauruscorp.sexify_android.application.di.modules.databases

import com.lauruscorp.sexify_android.application.di.modules.databases.cache.CacheDatabaseModule
import com.lauruscorp.sexify_android.application.di.modules.databases.tasks.TasksDatabaseModule
import dagger.Module

@Module(
    includes = [
        TasksDatabaseModule::class,
        CacheDatabaseModule::class
    ]
)
internal class DatabasesModule