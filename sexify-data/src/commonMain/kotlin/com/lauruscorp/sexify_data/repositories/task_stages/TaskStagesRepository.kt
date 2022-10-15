package com.lauruscorp.sexify_data.repositories.task_stages

import com.lauruscorp.sexify_data.entities.Task

interface TaskStagesRepository {
    suspend fun getAll(): List<Task.Stage>
}