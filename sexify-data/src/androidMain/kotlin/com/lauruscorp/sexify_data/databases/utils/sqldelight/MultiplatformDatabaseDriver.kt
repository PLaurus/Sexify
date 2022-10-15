package com.lauruscorp.sexify_data.databases.utils.sqldelight

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import com.squareup.sqldelight.Transacter
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlCursor
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.db.SqlPreparedStatement

internal actual class MultiplatformDatabaseDriver : SqlDriver {
    private val androidSqliteDriver: AndroidSqliteDriver

    constructor(
        openHelper: SupportSQLiteOpenHelper
    ) {
        androidSqliteDriver = AndroidSqliteDriver(openHelper)
    }

    @JvmOverloads
    constructor(
        schema: SqlDriver.Schema,
        context: Context,
        name: String? = null,
        factory: SupportSQLiteOpenHelper.Factory = FrameworkSQLiteOpenHelperFactory(),
        callback: SupportSQLiteOpenHelper.Callback = AndroidSqliteDriver.Callback(schema),
        cacheSize: Int = DEFAULT_CACHE_SIZE,
        useNoBackupDirectory: Boolean = false
    ) {
        androidSqliteDriver = AndroidSqliteDriver(
            schema, context, name, factory, callback, cacheSize, useNoBackupDirectory
        )
    }

    @JvmOverloads
    constructor(
        database: SupportSQLiteDatabase,
        cacheSize: Int = DEFAULT_CACHE_SIZE
    ) {
        androidSqliteDriver = AndroidSqliteDriver(database, cacheSize)
    }

    override fun close() = androidSqliteDriver.close()

    override fun currentTransaction(): Transacter.Transaction? {
        return androidSqliteDriver.currentTransaction()
    }

    override fun execute(
        identifier: Int?,
        sql: String, parameters: Int,
        binders: (SqlPreparedStatement.() -> Unit)?
    ) {
        return androidSqliteDriver.execute(identifier, sql, parameters, binders)
    }

    override fun executeQuery(
        identifier: Int?,
        sql: String,
        parameters: Int,
        binders: (SqlPreparedStatement.() -> Unit)?
    ): SqlCursor {
        return androidSqliteDriver.executeQuery(identifier, sql, parameters, binders)
    }

    override fun newTransaction(): Transacter.Transaction {
        return androidSqliteDriver.newTransaction()
    }

    companion object {
        private const val DEFAULT_CACHE_SIZE = 20
    }
}