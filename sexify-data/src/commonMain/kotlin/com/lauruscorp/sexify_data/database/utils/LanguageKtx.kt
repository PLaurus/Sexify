package com.lauruscorp.sexify_data.database.utils

import com.lauruscorp.sexify_data.database.SexifyDatabase
import com.lauruscorp.sexifydata.database.tables.Language
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOneOrNull
import kotlinx.coroutines.flow.Flow

fun SexifyDatabase.selectAllLanguages(): List<Language> {
	return languageQueries.selectAllLanguages()
		.executeAsList()
}

fun SexifyDatabase.flowSelectAllLanguages(): Flow<List<Language>> {
	return languageQueries.selectAllLanguages()
		.asFlow()
		.mapToList()
}

fun SexifyDatabase.selectLanguageById(
	id: String
): Language? {
	return languageQueries.selectLanguageById(id)
		.executeAsOneOrNull()
}

fun SexifyDatabase.flowSelectLanguageById(
	id: String
): Flow<Language?> {
	return languageQueries.selectLanguageById(id)
		.asFlow()
		.mapToOneOrNull()
}