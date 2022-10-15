package com.lauruscorp.sexify_data.databases.utils.sqldelight

import co.touchlab.sqliter.DatabaseConfiguration
import co.touchlab.sqliter.DatabaseManager
import com.squareup.sqldelight.Transacter
import com.squareup.sqldelight.db.SqlCursor
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.db.SqlPreparedStatement
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

internal actual class MultiplatformDatabaseDriver : SqlDriver {
    private val nativeSqliteDriver: NativeSqliteDriver

    constructor(
        databaseManager: DatabaseManager,
        maxReaderConnections: Int = 1,
    ) {
        nativeSqliteDriver = NativeSqliteDriver(databaseManager, maxReaderConnections)
    }

    constructor(
        configuration: DatabaseConfiguration,
        maxReaderConnections: Int = 1
    ) {
        nativeSqliteDriver = NativeSqliteDriver(configuration, maxReaderConnections)
    }

    constructor(
        schema: SqlDriver.Schema,
        name: String,
        maxReaderConnections: Int = 1
    ) {
        nativeSqliteDriver = NativeSqliteDriver(schema, name, maxReaderConnections)
    }

    override fun close() {
        nativeSqliteDriver.close()
    }

    override fun currentTransaction(): Transacter.Transaction? {
        return nativeSqliteDriver.currentTransaction()
    }

    override fun execute(
        identifier: Int?,
        sql: String,
        parameters: Int,
        binders: (SqlPreparedStatement.() -> Unit)?
    ) {
        nativeSqliteDriver.execute(identifier, sql, parameters, binders)
    }

    override fun executeQuery(
        identifier: Int?,
        sql: String,
        parameters: Int,
        binders: (SqlPreparedStatement.() -> Unit)?
    ): SqlCursor {
        return nativeSqliteDriver.executeQuery(identifier, sql, parameters, binders)
    }

    override fun newTransaction(): Transacter.Transaction {
        return nativeSqliteDriver.newTransaction()
    }
}