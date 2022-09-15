package com.lauruscorp.sexify_data.database

import android.content.Context
import com.lauruscorp.sexify_data.database.utils.MultiplatformDatabaseDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(
	private val context: Context,
	private val databaseName: String
) {
	actual fun create(): SqlDriver {
		return MultiplatformDatabaseDriver(
			schema = SexifyDatabase.Schema,
			context = context,
			name = databaseName
		)
	}
}