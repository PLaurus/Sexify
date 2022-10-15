package com.lauruscorp.sexify_data.repositories.languages

import com.lauruscorp.sexify_data.data_sources.languages.LanguagesLocalDataSource
import com.lauruscorp.sexify_data.entities.SexifyLanguage

class LocalLanguagesRepository(
    private val languagesLocalDataSource: LanguagesLocalDataSource
) : LanguagesRepository {
    override suspend fun readAll(): List<SexifyLanguage> {
        return languagesLocalDataSource.getAll()
    }

    override suspend fun read(id: String): SexifyLanguage? {
        return languagesLocalDataSource.get(id)
    }
}