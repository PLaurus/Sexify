package com.lauruscorp.sexify_data.data_sources.languages

import com.lauruscorp.sexify_data.data_sources.mapping.toSexifyLanguage
import com.lauruscorp.sexify_data.databases.tasks.TasksDatabase
import com.lauruscorp.sexify_data.databases.tasks.utils.selectAllLanguages
import com.lauruscorp.sexify_data.databases.tasks.utils.selectLanguageById
import com.lauruscorp.sexify_data.entities.SexifyLanguage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class LanguagesSqlDelightDataSource(
    private val database: TasksDatabase,
    private val ioDispatcher: CoroutineDispatcher
) : LanguagesLocalDataSource {
    override suspend fun getAll(): List<SexifyLanguage> = withContext(ioDispatcher) {
        database.transactionWithResult {
            val dbLanguages = database.selectAllLanguages()
            dbLanguages.mapNotNull { it.toSexifyLanguage() }
        }
    }

    override suspend fun get(id: String): SexifyLanguage? = withContext(ioDispatcher) {
        database.transactionWithResult {
            val dbLanguage = database.selectLanguageById(id)
            dbLanguage?.toSexifyLanguage()
        }
    }
}