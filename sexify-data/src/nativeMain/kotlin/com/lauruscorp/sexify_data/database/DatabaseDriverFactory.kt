package com.lauruscorp.sexify_data.database

import com.lauruscorp.sexify_data.database.utils.MultiplatformDatabaseDriver
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