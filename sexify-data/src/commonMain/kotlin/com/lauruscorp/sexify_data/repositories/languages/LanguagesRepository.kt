package com.lauruscorp.sexify_data.repositories.languages

import com.lauruscorp.sexify_data.entities.SexifyLanguage

interface LanguagesRepository {
    suspend fun readAll(): List<SexifyLanguage>
    suspend fun read(id: String): SexifyLanguage?
}