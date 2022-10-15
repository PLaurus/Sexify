package com.lauruscorp.sexify_data.data_sources.sexes

import com.lauruscorp.sexify_data.data_sources.mapping.asSexifySex
import com.lauruscorp.sexify_data.databases.tasks.TasksDatabase
import com.lauruscorp.sexify_data.databases.tasks.utils.selectAllSexes
import com.lauruscorp.sexify_data.entities.SexifySex
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class SexesSqlDelightDataSource(
    private val database: TasksDatabase,
    private val ioDispatcher: CoroutineDispatcher
) : SexesLocalDataSource {
    override suspend fun getAll(): List<SexifySex> = withContext(ioDispatcher) {
        database.selectAllSexes()
            .mapNotNull { dbSex -> dbSex.asSexifySex() }
    }
}