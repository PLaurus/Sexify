package com.lauruscorp.sexify_data.repositories.sexes

import com.lauruscorp.sexify_data.entities.SexifySex

interface SexesRepository {
    suspend fun readAll(): List<SexifySex>
}