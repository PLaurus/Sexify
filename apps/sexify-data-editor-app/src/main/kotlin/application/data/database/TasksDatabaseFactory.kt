package application.data.database

import application.di.modules.databases.tasks.qualifiers.TasksDatabaseFileNameQualifier
import application.di.modules.databases.tasks.qualifiers.TasksDatabasePathQualifier
import com.lauruscorp.sexify_data.databases.tasks.SafeTasksDatabase
import com.lauruscorp.sexify_data.databases.tasks.TasksDatabase
import com.lauruscorp.sexify_data.databases.utils.sqldelight.DatabaseDriverFactory
import com.squareup.sqldelight.db.SqlDriver
import java.nio.file.Path
import javax.inject.Inject

class TasksDatabaseFactory @Inject constructor(
    @TasksDatabasePathQualifier private val dbFilePath: Path,
    @TasksDatabaseFileNameQualifier private val dbFileName: String
) {
    fun create(): TasksDatabase {
        val driver = createDriver()
        return SafeTasksDatabase(driver)
    }

    private fun createDriver(): SqlDriver {
        return DatabaseDriverFactory(dbFilePath, dbFileName).create()
    }
}