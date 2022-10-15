package com.lauruscorp.sexify_data.repositories.task_stages

import com.lauruscorp.sexify_data.data_sources.task_stages.TaskStagesLocalDataSource
import com.lauruscorp.sexify_data.entities.Task

class LocalTaskStagesRepository(
    private val taskStagesLocalDataSource: TaskStagesLocalDataSource
) : TaskStagesRepository {
    override suspend fun getAll(): List<Task.Stage> {
        return taskStagesLocalDataSource.getAll()
    }
}