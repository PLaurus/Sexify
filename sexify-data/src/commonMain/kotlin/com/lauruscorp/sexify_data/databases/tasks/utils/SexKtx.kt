package com.lauruscorp.sexify_data.databases.tasks.utils

import com.lauruscorp.sexify_data.databases.tasks.TasksDatabase
import com.lauruscorp.sexifydata.databases.tasks.tables.Sex
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOneOrNull
import kotlinx.coroutines.flow.Flow

fun TasksDatabase.selectAllSexes(): List<Sex> {
	return sexQueries.selectAllSexes()
		.executeAsList()
}

fun TasksDatabase.flowSelectAllSexes(): Flow<List<Sex>> {
	return sexQueries.selectAllSexes()
		.asFlow()
		.mapToList()
}

fun TasksDatabase.selectSexById(
	id: Long
): Sex? {
	return sexQueries.selectSexById(id)
		.executeAsOneOrNull()
}

fun TasksDatabase.flowSelectSexById(
	id: Long
): Flow<Sex?> {
	return sexQueries.selectSexById(id)
		.asFlow()
		.mapToOneOrNull()
}

fun TasksDatabase.selectSexByEnumName(
	enumName: String
): Sex? {
	return sexQueries.selectSexByEnumName(enumName)
		.executeAsOneOrNull()
}

fun TasksDatabase.flowSelectSexByDebugName(
	enumName: String
): Flow<Sex?> {
	return sexQueries.selectSexByEnumName(enumName)
		.asFlow()
		.mapToOneOrNull()
}

fun TasksDatabase.insertSex(enumName: String) {
	sexQueries.insertSex(enumName)
}