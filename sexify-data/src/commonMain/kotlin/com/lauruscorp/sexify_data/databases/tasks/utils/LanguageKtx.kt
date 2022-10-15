package com.lauruscorp.sexify_data.databases.tasks.utils

import com.lauruscorp.sexify_data.databases.tasks.TasksDatabase
import com.lauruscorp.sexifydata.databases.tasks.tables.Language
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOneOrNull
import kotlinx.coroutines.flow.Flow

fun TasksDatabase.selectAllLanguages(): List<Language> {
	return languageQueries.selectAllLanguages()
		.executeAsList()
}

fun TasksDatabase.flowSelectAllLanguages(): Flow<List<Language>> {
	return languageQueries.selectAllLanguages()
		.asFlow()
		.mapToList()
}

fun TasksDatabase.selectLanguageById(
	id: String
): Language? {
	return languageQueries.selectLanguageById(id)
		.executeAsOneOrNull()
}

fun TasksDatabase.flowSelectLanguageById(
	id: String
): Flow<Language?> {
	return languageQueries.selectLanguageById(id)
		.asFlow()
		.mapToOneOrNull()
}

fun TasksDatabase.insertLanguage(
	id: String,
	name: String
) {
	languageQueries.insertLanguage(id = id, languageName = name)
}

fun TasksDatabase.updateLanguageById(
	id: String,
	languageName: String
) {
	languageQueries.updateLanguageById(languageName, id)
}