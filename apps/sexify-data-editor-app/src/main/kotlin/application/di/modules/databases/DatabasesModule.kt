package application.di.modules.databases

import application.di.modules.databases.tasks.TasksDatabaseModule
import dagger.Module

@Module(
    includes = [
        TasksDatabaseModule::class
    ]
)
internal class DatabasesModule