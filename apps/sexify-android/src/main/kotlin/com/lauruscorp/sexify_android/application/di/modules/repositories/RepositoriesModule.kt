package com.lauruscorp.sexify_android.application.di.modules.repositories

import com.lauruscorp.sexify_data.data_sources.languages.LanguagesLocalDataSource
import com.lauruscorp.sexify_data.data_sources.players.PlayersLocalDataSource
import com.lauruscorp.sexify_data.data_sources.sexes.SexesLocalDataSource
import com.lauruscorp.sexify_data.data_sources.task_stages.TaskStagesLocalDataSource
import com.lauruscorp.sexify_data.data_sources.tasks.TasksLocalDataSource
import com.lauruscorp.sexify_data.repositories.languages.LanguagesRepository
import com.lauruscorp.sexify_data.repositories.languages.LocalLanguagesRepository
import com.lauruscorp.sexify_data.repositories.players.LocalPlayersRepository
import com.lauruscorp.sexify_data.repositories.players.PlayersRepository
import com.lauruscorp.sexify_data.repositories.sexes.LocalSexesRepository
import com.lauruscorp.sexify_data.repositories.sexes.SexesRepository
import com.lauruscorp.sexify_data.repositories.task_stages.LocalTaskStagesRepository
import com.lauruscorp.sexify_data.repositories.task_stages.TaskStagesRepository
import com.lauruscorp.sexify_data.repositories.tasks.LocalTasksRepository
import com.lauruscorp.sexify_data.repositories.tasks.TasksRepository
import dagger.Module
import dagger.Provides

@Module
internal class RepositoriesModule {
    @Provides
    fun provideTasksRepository(
        tasksLocalDataSource: TasksLocalDataSource
    ): TasksRepository {
        return LocalTasksRepository(tasksLocalDataSource)
    }

    @Provides
    fun provideTaskStagesRepository(
        taskStagesLocalDataSource: TaskStagesLocalDataSource
    ): TaskStagesRepository {
        return LocalTaskStagesRepository(taskStagesLocalDataSource)
    }

    @Provides
    fun provideLanguagesRepository(
        languagesLocalDataSource: LanguagesLocalDataSource
    ): LanguagesRepository {
        return LocalLanguagesRepository(languagesLocalDataSource)
    }

    @Provides
    fun provideSexesRepository(
        sexesLocalDataSource: SexesLocalDataSource
    ): SexesRepository {
        return LocalSexesRepository(sexesLocalDataSource)
    }

    @Provides
    fun providePlayersRepository(
        playersLocalDataSource: PlayersLocalDataSource
    ): PlayersRepository {
        return LocalPlayersRepository(playersLocalDataSource)
    }
}