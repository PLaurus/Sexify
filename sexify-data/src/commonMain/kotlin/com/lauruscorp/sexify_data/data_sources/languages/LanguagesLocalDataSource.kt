package com.lauruscorp.sexify_data.data_sources.languages

import com.lauruscorp.sexify_data.entities.SexifyLanguage

interface LanguagesLocalDataSource {
    suspend fun getAll(): List<SexifyLanguage>
    suspend fun get(id: String): SexifyLanguage?
}