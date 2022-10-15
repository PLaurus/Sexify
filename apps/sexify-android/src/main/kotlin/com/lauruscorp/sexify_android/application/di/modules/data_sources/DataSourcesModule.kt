package com.lauruscorp.sexify_android.application.di.modules.data_sources

import com.lauruscorp.sexify_android.application.di.modules.coroutines.qualifiers.IoCoroutineDispatcherQualifier
import com.lauruscorp.sexify_data.data_sources.languages.LanguagesLocalDataSource
import com.lauruscorp.sexify_data.data_sources.languages.LanguagesSqlDelightDataSource
import com.lauruscorp.sexify_data.data_sources.players.PlayersLocalDataSource
import com.lauruscorp.sexify_data.data_sources.players.PlayersSqlDelightDataSource
import com.lauruscorp.sexify_data.data_sources.sexes.SexesLocalDataSource
import com.lauruscorp.sexify_data.data_sources.sexes.SexesSqlDelightDataSource
import com.lauruscorp.sexify_data.data_sources.task_stages.TaskStagesLocalDataSource
import com.lauruscorp.sexify_data.data_sources.task_stages.TaskStagesSqlDelightDataSource
import com.lauruscorp.sexify_data.data_sources.tasks.TasksLocalDataSource
import com.lauruscorp.sexify_data.data_sources.tasks.TasksSqlDelightDataSource
import com.lauruscorp.sexify_data.databases.tasks.TasksDatabase
import com.lauruscorp.sexifydata.databases.cache.tables.PlayerQueries
import com.lauruscorp.sexifydata.databases.cache.utils.UtilsQueries
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher

@Module
internal class DataSourcesModule {
    @Provides
    fun provideTasksLocalDataSource(
        database: TasksDatabase,
        @IoCoroutineDispatcherQualifier ioDispatcher: CoroutineDispatcher
    ): TasksLocalDataSource {
        return TasksSqlDelightDataSource(database, ioDispatcher)
    }

    @Provides
    fun provideTaskStagesLocalDataSource(
        database: TasksDatabase,
        @IoCoroutineDispatcherQualifier ioDispatcher: CoroutineDispatcher
    ): TaskStagesLocalDataSource {
        return TaskStagesSqlDelightDataSource(database, ioDispatcher)
    }

    @Provides
    fun provideLanguagesLocalDataSource(
        database: TasksDatabase,
        @IoCoroutineDispatcherQualifier ioDispatcher: CoroutineDispatcher
    ): LanguagesLocalDataSource {
        return LanguagesSqlDelightDataSource(database, ioDispatcher)
    }

    @Provides
    fun provideSexesLocalDataSource(
        database: TasksDatabase,
        @IoCoroutineDispatcherQualifier ioDispatcher: CoroutineDispatcher
    ): SexesLocalDataSource {
        return SexesSqlDelightDataSource(database, ioDispatcher)
    }

    @Provides
    fun providePlayersLocalDataSource(
        playerQueries: PlayerQueries,
        utilsQueries: UtilsQueries,
        @IoCoroutineDispatcherQualifier ioDispatcher: CoroutineDispatcher
    ): PlayersLocalDataSource {
        return PlayersSqlDelightDataSource(playerQueries, utilsQueries, ioDispatcher)
    }
}