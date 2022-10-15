package com.lauruscorp.sexify_data.data_sources.tasks

import com.lauruscorp.core.util.Result
import com.lauruscorp.sexify_data.entities.SexifySex
import com.lauruscorp.sexify_data.entities.Task
import com.lauruscorp.sexify_data.entities.Text
import com.lauruscorp.sexify_data.entities.errors.TasksDataError
import kotlinx.coroutines.flow.Flow

interface TasksLocalDataSource {
    suspend fun insert(
        text: Text,
        textTranslations: List<Text>,
        stageId: Long,
        doerSexes: List<SexifySex>,
        partnerSexes: List<SexifySex>,
        timerSec: Int? = null
    ): Result<Task, TasksDataError>

    suspend fun getAll(): Result<List<Task>, TasksDataError>
    fun getAllFlow(): Flow<Result<List<Task>, TasksDataError>>
    suspend fun get(id: Long): Result<Task?, TasksDataError>
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