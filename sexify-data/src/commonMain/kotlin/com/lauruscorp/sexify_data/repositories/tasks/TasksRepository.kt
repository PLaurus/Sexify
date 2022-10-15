package com.lauruscorp.sexify_data.repositories.tasks

import com.lauruscorp.core.util.Result
import com.lauruscorp.sexify_data.entities.SexifySex
import com.lauruscorp.sexify_data.entities.Task
import com.lauruscorp.sexify_data.entities.Text
import com.lauruscorp.sexify_data.entities.errors.TasksDataError
import kotlinx.coroutines.flow.Flow

interface TasksRepository {
    suspend fun create(
        text: Text,
        textTranslations: List<Text>,
        stageId: Long,
        doerSexes: List<SexifySex>,
        partnerSexes: List<SexifySex>,
        timerSec: Int? = null
    ): Result<Task, TasksDataError>

    suspend fun readAll(): Result<List<Task>, TasksDataError>
    fun readAllFlow(): Flow<Result<List<Task>, TasksDataError>>
    suspend fun read(id: Long): Result<Task?, TasksDataError>
    suspend fun update(
        id: Long,
        text: Text,
        textTranslations: List<Text>,
        stageId: Long,
        doerSexes: List<SexifySex>,
        partnerSexes: List<SexifySex>,
        timerSec: Int? = null
    ): Result<Task, TasksDataError>

    suspend fun delete(id: Long)
}