package com.lauruscorp.sexify_data.repositories.tasks

import com.lauruscorp.core.util.Result
import com.lauruscorp.sexify_data.data_sources.tasks.TasksLocalDataSource
import com.lauruscorp.sexify_data.entities.SexifySex
import com.lauruscorp.sexify_data.entities.Task
import com.lauruscorp.sexify_data.entities.Text
import com.lauruscorp.sexify_data.entities.errors.TasksDataError
import kotlinx.coroutines.flow.Flow

class LocalTasksRepository(
    private val tasksLocalDataSource: TasksLocalDataSource
) : TasksRepository {
    override suspend fun create(
        text: Text,
        textTranslations: List<Text>,
        stageId: Long,
        doerSexes: List<SexifySex>,
        partnerSexes: List<SexifySex>,
        timerSec: Int?
    ): Result<Task, TasksDataError> {
        return tasksLocalDataSource.insert(
            text,
            textTranslations,
            stageId,
            doerSexes,
            partnerSexes,
            timerSec
        )
    }

    override suspend fun readAll(): Result<List<Task>, TasksDataError> {
        return tasksLocalDataSource.getAll()
    }

    override fun readAllFlow(): Flow<Result<List<Task>, TasksDataError>> {
        return tasksLocalDataSource.getAllFlow()
    }

    override suspend fun read(id: Long): Result<Task?, TasksDataError> {
        return tasksLocalDataSource.get(id)
    }

    override suspend fun update(
        id: Long,
        text: Text,
        textTranslations: List<Text>,
        stageId: Long,
        doerSexes: List<SexifySex>,
        partnerSexes: List<SexifySex>,
        timerSec: Int?
    ): Result<Task, TasksDataError> {
        return tasksLocalDataSource.update(
            id,
            text,
            textTranslations,
            stageId,
            doerSexes,
            partnerSexes,
            timerSec
        )
    }

    override suspend fun delete(id: Long) {
        tasksLocalDataSource.delete(id)
    }
}