package com.lauruscorp.sexify_android.application.di.modules.databases.tasks

import android.content.Context
import com.lauruscorp.core_android.di.dagger.qualifiers.context.ApplicationContextQualifier
import com.lauruscorp.sexify_android.application.di.component.scope.SexifyApplicationScope
import com.lauruscorp.sexify_android.application.di.modules.databases.tasks.qualifiers.TasksDatabaseAssetPathQualifier
import com.lauruscorp.sexify_android.application.di.modules.databases.tasks.qualifiers.TasksDatabaseDriverFactoryQualifier
import com.lauruscorp.sexify_android.application.di.modules.databases.tasks.qualifiers.TasksDatabaseFileNameQualifier
import com.lauruscorp.sexify_android.application.di.modules.databases.tasks.qualifiers.TasksDatabaseSqlDriverQualifier
import com.lauruscorp.sexify_data.databases.tasks.SafeTasksDatabase
import com.lauruscorp.sexify_data.databases.tasks.TasksDatabase
import com.lauruscorp.sexify_data.databases.utils.sqldelight.DatabaseDriverFactory
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import java.nio.file.Path
import kotlin.io.path.Path

@Module
internal class TasksDatabaseModule {
    @SexifyApplicationScope
    @Provides
    fun provideTasksDatabase(
        @TasksDatabaseSqlDriverQualifier sqlDriver: SqlDriver
    ): TasksDatabase {
        return SafeTasksDatabase(sqlDriver)
    }

    @Provides
    @TasksDatabaseSqlDriverQualifier
    fun provideSqlDriver(
        @TasksDatabaseDriverFactoryQualifier databaseDriverFactory: DatabaseDriverFactory
    ): SqlDriver {
        return databaseDriverFactory.create()
    }

    @Provides
    @TasksDatabaseDriverFactoryQualifier
    fun provideDatabaseDriverFactory(
        @ApplicationContextQualifier context: Context,
        @TasksDatabaseFileNameQualifier dbFileName: String
    ): DatabaseDriverFactory {
        return DatabaseDriverFactory(
            context = context,
            databaseName = dbFileName
        )
    }

    @Provides
    @TasksDatabaseAssetPathQualifier
    fun provideTasksDatabaseAssetPath(
        @TasksDatabaseFileNameQualifier dbFileName: String
    ): Path {
        return Path("databases", dbFileName)
    }

    @Provides
    @TasksDatabaseFileNameQualifier
    fun provideTasksDatabaseFileName(): String {
        return "tasks.db"
    }
}