package com.lauruscorp.sexify_data.data_sources.task_stages

import com.lauruscorp.sexify_data.data_sources.mapping.asTaskStage
import com.lauruscorp.sexify_data.databases.tasks.TasksDatabase
import com.lauruscorp.sexify_data.databases.tasks.utils.selectAllTaskStages
import com.lauruscorp.sexify_data.entities.Task
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class TaskStagesSqlDelightDataSource(
    private val database: TasksDatabase,
    private val ioDispatcher: CoroutineDispatcher
) : TaskStagesLocalDataSource {
    override suspend fun getAll(): List<Task.Stage> = withContext(ioDispatcher) {
        database.transactionWithResult {
            database.selectAllTaskStages()
                .mapNotNull { dbTaskStage ->
                    dbTaskStage.asTaskStage(database)
                }
        }
    }
}