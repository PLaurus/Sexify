package com.lauruscorp.sexify_data.databases.utils.sqldelight

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import java.util.*

/**
 * @param url Database connection URL in the form of `jdbc:sqlite:path` where `path` is either blank
 * (creating an in-memory database) or a path to a file.
 *
 * Example of connection string to in-memory database: jdbc:sqlite::memory:
 *
 * Example of connection string with relative path: jdbc:sqlite:sample.db
 *
 * Example of connection string with absolute path: jdbc:sqlite:C:/sqlite/db/sample.db
 */
internal actual class MultiplatformDatabaseDriver(
    url: String,
    properties: Properties = Properties()
) : SqlDriver by JdbcSqliteDriver(url, properties)