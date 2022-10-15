package com.lauruscorp.sexify_data.databases.tasks.utils

import com.lauruscorp.sexify_data.databases.tasks.TasksDatabase
import com.lauruscorp.sexifydata.databases.tasks.tables.Sex
import com.lauruscorp.sexifydata.databases.tasks.tables.Task
import com.lauruscorp.sexifydata.databases.tasks.tables.TaskDoerSex
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow

fun TaskDoerSex.getTask(
	database: TasksDatabase
): Task? {
	return database.selectTaskById(taskId)
}

fun TaskDoerSex.flowGetTask(
	database: TasksDatabase
): Flow<Task?> {
	return database.flowSelectTaskById(taskId)
}

fun TaskDoerSex.getSex(
	database: TasksDatabase
): Sex? {
	return database.selectSexById(sexId)
}

fun TaskDoerSex.flowGetSex(
	database: TasksDatabase
): Flow<Sex?> {
	return database.flowSelectSexById(sexId)
}

fun TasksDatabase.selectTaskDoerSexesByTaskId(
	taskId: Long
): List<TaskDoerSex> {
	return taskDoerSexQueries.selectTaskDoerSexesByTaskId(taskId)
		.executeAsList()
}

fun TasksDatabase.flowSelectTaskDoerSexesByTaskId(
	taskId: Long
): Flow<List<TaskDoerSex>> {
	return taskDoerSexQueries.selectTaskDoerSexesByTaskId(taskId)
		.asFlow()
		.mapToList()
}

fun TasksDatabase.insertTaskDoerSex(
	sex: TaskDoerSex
): Long? {
	taskDoerSexQueries.insertTaskDoerSex(sex)
	return selectLastInsertRowId()
}

fun TasksDatabase.insertTaskDoerSexFields(
	taskId: Long,
	sexId: Long
): Long? {
	taskDoerSexQueries.insertTaskDoerSexFields(taskId, sexId)
	return selectLastInsertRowId()
}

fun TasksDatabase.deleteTaskDoerSex(
	taskId: Long,
	sexId: Long
) {
	taskDoerSexQueries.deleteTaskDoerSex(taskId, sexId)
}

fun TasksDatabase.deleteTaskDoerSexesByTaskId(
	taskId: Long
) {
	taskDoerSexQueries.deleteTaskDoerSexesByTaskId(taskId)
}