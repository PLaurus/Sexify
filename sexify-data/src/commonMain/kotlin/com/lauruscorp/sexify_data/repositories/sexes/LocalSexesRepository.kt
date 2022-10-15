package com.lauruscorp.sexify_data.repositories.sexes

import com.lauruscorp.sexify_data.data_sources.sexes.SexesLocalDataSource
import com.lauruscorp.sexify_data.entities.SexifySex

class LocalSexesRepository(
    private val sexesLocalDataSource: SexesLocalDataSource
) : SexesRepository {
    override suspend fun readAll(): List<SexifySex> = sexesLocalDataSource.getAll()
}