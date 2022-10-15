package com.lauruscorp.sexify_android.application.di.modules.databases.cache

import android.content.Context
import com.lauruscorp.core_android.di.dagger.qualifiers.context.ApplicationContextQualifier
import com.lauruscorp.sexify_android.application.di.component.scope.SexifyApplicationScope
import com.lauruscorp.sexify_android.application.di.modules.databases.cache.qualifiers.CacheDatabaseDriverFactoryQualifier
import com.lauruscorp.sexify_android.application.di.modules.databases.cache.qualifiers.CacheDatabaseFileNameQualifier
import com.lauruscorp.sexify_android.application.di.modules.databases.cache.qualifiers.CacheDatabaseSqlDriverQualifier
import com.lauruscorp.sexify_data.databases.cache.CacheDatabase
import com.lauruscorp.sexify_data.databases.utils.sqldelight.DatabaseDriverFactory
import com.lauruscorp.sexifydata.databases.cache.tables.PlayerQueries
import com.lauruscorp.sexifydata.databases.cache.utils.UtilsQueries
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides

@Module
internal class CacheDatabaseModule {
    @SexifyApplicationScope
    @Provides
    fun provideCacheDatabase(
        @CacheDatabaseSqlDriverQualifier sqlDriver: SqlDriver
    ): CacheDatabase {
        return CacheDatabase(sqlDriver)
    }

    @Provides
    @CacheDatabaseSqlDriverQualifier
    fun provideSqlDriver(
        @CacheDatabaseDriverFactoryQualifier databaseDriverFactory: DatabaseDriverFactory
    ): SqlDriver {
        return databaseDriverFactory.create()
    }

    @Provides
    @CacheDatabaseDriverFactoryQualifier
    fun provideDatabaseDriverFactory(
        @ApplicationContextQualifier context: Context,
        @CacheDatabaseFileNameQualifier dbFileName: String
    ): DatabaseDriverFactory {
        return DatabaseDriverFactory(
            context = context,
            databaseName = dbFileName
        )
    }

    @Provides
    @CacheDatabaseFileNameQualifier
    fun provideCacheDatabaseFileName(): String {
        return "cache.db"
    }

    @Provides
    fun providePlayerQueries(
        cacheDatabase: CacheDatabase
    ): PlayerQueries {
        return cacheDatabase.playerQueries
    }

    @Provides
    fun provideUtilsQueries(
        cacheDatabase: CacheDatabase
    ): UtilsQueries {
        return cacheDatabase.utilsQueries
    }
}