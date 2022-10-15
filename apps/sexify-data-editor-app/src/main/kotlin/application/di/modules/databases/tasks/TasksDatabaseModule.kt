package application.di.modules.databases.tasks

import application.di.component.scope.ApplicationScope
import application.di.modules.databases.tasks.qualifiers.TasksDatabaseDriverFactoryQualifier
import application.di.modules.databases.tasks.qualifiers.TasksDatabaseFileNameQualifier
import application.di.modules.databases.tasks.qualifiers.TasksDatabasePathQualifier
import application.di.modules.databases.tasks.qualifiers.TasksDatabaseSqlDriverQualifier
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
    @ApplicationScope
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
        @TasksDatabasePathQualifier dbFilePath: Path,
        @TasksDatabaseFileNameQualifier dbFileName: String
    ): DatabaseDriverFactory {
        return DatabaseDriverFactory(
            path = dbFilePath,
            databaseName = dbFileName
        )
    }

    @Provides
    @TasksDatabasePathQualifier
    fun provideSexifyDatabasePath(): Path {
        return Path("database")
    }

    @Provides
    @TasksDatabaseFileNameQualifier
    fun provideTasksDatabaseFileName(): String {
        return "tasks.db"
    }
}