package com.lauruscorp.sexify_data.database

import com.squareup.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {
	fun create(): SqlDriver
}