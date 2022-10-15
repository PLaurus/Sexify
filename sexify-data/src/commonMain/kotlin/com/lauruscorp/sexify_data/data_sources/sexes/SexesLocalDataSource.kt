package com.lauruscorp.sexify_data.data_sources.sexes

import com.lauruscorp.sexify_data.entities.SexifySex

interface SexesLocalDataSource {
    suspend fun getAll(): List<SexifySex>
}