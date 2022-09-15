package com.lauruscorp.sexify_data.database.utils

import com.lauruscorp.sexify_data.database.SexifyDatabase
import com.lauruscorp.sexifydata.database.tables.Sex
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOneOrNull
import kotlinx.coroutines.flow.Flow

fun Sex.getName(
	database: SexifyDatabase,
	languageId: String? = null
): String? {
	return database.getText(textContentId = nameTextId, languageId = languageId)
}

fun SexifyDatabase.selectAllSexes(): List<Sex> {
	return sexQueries.selectAllSexes()
		.executeAsList()
}

fun SexifyDatabase.flowSelectAllSexes(): Flow<List<Sex>> {
	return sexQueries.selectAllSexes()
		.asFlow()
		.mapToList()
}

fun SexifyDatabase.selectSexById(id: Long): Sex? {
	return sexQueries.selectSexById(id)
		.executeAsOneOrNull()
}

fun SexifyDatabase.flowSelectSexById(id: Long): Flow<Sex?> {
	return sexQueries.selectSexById(id)
		.asFlow()
		.mapToOneOrNull()
}