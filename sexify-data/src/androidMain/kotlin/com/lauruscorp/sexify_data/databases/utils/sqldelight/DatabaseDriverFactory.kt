package com.lauruscorp.sexify_data.databases.utils.sqldelight

import android.content.Context
import com.lauruscorp.sexify_data.databases.tasks.TasksDatabase
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(
    private val context: Context,
    private val databaseName: String
) {
    actual fun create(): SqlDriver {
        return MultiplatformDatabaseDriver(
            schema = TasksDatabase.Schema,
            context = context,
            name = databaseName
        )
    }
}