package com.lauruscorp.sexify_data.data_sources.task_stages

import com.lauruscorp.sexify_data.entities.Task

interface TaskStagesLocalDataSource {
    suspend fun getAll(): List<Task.Stage>
}