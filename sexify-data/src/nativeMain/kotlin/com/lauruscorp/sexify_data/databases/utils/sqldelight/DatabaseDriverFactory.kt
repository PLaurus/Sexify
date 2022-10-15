package com.lauruscorp.sexify_data.databases.utils.sqldelight

import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(
    private val databaseName: String
) {
    actual fun create(): SqlDriver {
        return MultiplatformDatabaseDriver(
            schema = GeneratedDatabaseProxy.Schema,
            name = databaseName
        )
    }
}