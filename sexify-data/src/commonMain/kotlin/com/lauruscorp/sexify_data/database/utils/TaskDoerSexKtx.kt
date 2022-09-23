package com.lauruscorp.sexify_data.database.utils

import com.lauruscorp.sexify_data.database.SexifyDatabase
import com.lauruscorp.sexifydata.database.tables.Sex
import com.lauruscorp.sexifydata.database.tables.Task
import com.lauruscorp.sexifydata.database.tables.TaskDoerSex
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow

fun TaskDoerSex.getTask(
	database: SexifyDatabase
): Task? {
	return database.selectTaskById(taskId)
}

fun TaskDoerSex.flowGetTask(
	database: SexifyDatabase
): Flow<Task?> {
	return database.flowSelectTaskById(taskId)
}

fun TaskDoerSex.getSex(
	database: SexifyDatabase
): Sex? {
	return database.selectSexById(sexId)
}

fun TaskDoerSex.flowGetSex(
	database: SexifyDatabase
): Flow<Sex?> {
	return database.flowSelectSexById(sexId)
}

fun SexifyDatabase.selectTaskDoerSexesByTaskId(
	taskId: Long
): List<TaskDoerSex> {
	return taskDoerSexQueries.selectTaskDoerSexesByTaskId(taskId)
		.executeAsList()
}

fun SexifyDatabase.flowSelectTaskDoerSexesByTaskId(
	taskId: Long
): Flow<List<TaskDoerSex>> {
	return taskDoerSexQueries.selectTaskDoerSexesByTaskId(taskId)
		.asFlow()
		.mapToList()
}

fun SexifyDatabase.insertTaskDoerSex(
	sex: TaskDoerSex
): Long? {
	taskDoerSexQueries.insertTaskDoerSex(sex)
	return selectLastInsertRowId()
}

fun SexifyDatabase.insertTaskDoerSexFields(
	taskId: Long,
	sexId: Long
): Long? {
	taskDoerSexQueries.insertTaskDoerSexFields(taskId, sexId)
	return selectLastInsertRowId()
}

fun SexifyDatabase.deleteTaskDoerSex(
	taskId: Long,
	sexId: Long
) {
	taskDoerSexQueries.deleteTaskDoerSex(taskId, sexId)
}

fun SexifyDatabase.deleteTaskDoerSexesByTaskId(
	taskId: Long
) {
	taskDoerSexQueries.deleteTaskDoerSexesByTaskId(taskId)
}