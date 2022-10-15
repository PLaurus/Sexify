package com.lauruscorp.sexify_data.databases.utils.sqldelight

import com.squareup.sqldelight.db.SqlDriver
import java.nio.file.Path
import java.util.*

/**
 * @param path Path to database (existing or not) excluding database name. It can be absolute or relative.
 * @param databaseName Name of database file. Example: sample.db
 */
actual class DatabaseDriverFactory(
    private val path: Path,
    private val databaseName: String
) {
	actual fun create(): SqlDriver {
		return MultiplatformDatabaseDriver(
            url = createConnectionString(path),
            properties = Properties()
        )
	}

	private fun createConnectionString(
		path: Path
	): String {
		val fullPath = path.resolve(databaseName)
		return "$BASE_CONNECTION_STRING$fullPath"
	}

	companion object {
		private const val BASE_CONNECTION_STRING = "jdbc:sqlite:"
	}
}