package com.lauruscorp.sexify_data.databases.tasks.utils

import com.lauruscorp.sexify_data.databases.tasks.TasksDatabase
import com.lauruscorp.sexifydata.databases.tasks.tables.Sex
import com.lauruscorp.sexifydata.databases.tasks.tables.Task
import com.lauruscorp.sexifydata.databases.tasks.tables.TaskDoerPartnerSex
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow

fun TaskDoerPartnerSex.getTask(
	database: TasksDatabase
): Task? {
	return database.selectTaskById(taskId)
}

fun TaskDoerPartnerSex.flowGetTask(
	database: TasksDatabase
): Flow<Task?> {
	return database.flowSelectTaskById(taskId)
}

fun TaskDoerPartnerSex.getSex(
	database: TasksDatabase
): Sex? {
	return database.selectSexById(sexId)
}

fun TaskDoerPartnerSex.flowGetSex(
	database: TasksDatabase
): Flow<Sex?> {
	return database.flowSelectSexById(sexId)
}

fun TasksDatabase.selectTaskDoerPartnerSexesByTaskId(
	taskId: Long
): List<TaskDoerPartnerSex> {
	return taskDoerPartnerSexQueries.selectTaskDoerPartnerSexesByTaskId(taskId)
		.executeAsList()
}

fun TasksDatabase.flowSelectTaskDoerPartnerSexesByTaskId(
	taskId: Long
): Flow<List<TaskDoerPartnerSex>> {
	return taskDoerPartnerSexQueries.selectTaskDoerPartnerSexesByTaskId(taskId)
		.asFlow()
		.mapToList()
}

fun TasksDatabase.insertTaskDoerPartnerSex(
	sex: TaskDoerPartnerSex
): Long? {
	taskDoerPartnerSexQueries.insertTaskDoerPartnerSex(sex)
	return selectLastInsertRowId()
}

fun TasksDatabase.insertTaskDoerPartnerSexFields(
	taskId: Long,
	sexId: Long
): Long? {
	taskDoerPartnerSexQueries.insertTaskDoerPartnerSexFields(taskId, sexId)
	return selectLastInsertRowId()
}

fun TasksDatabase.deleteTaskDoerPartnerSex(
	taskId: Long,
	sexId: Long
) {
	taskDoerPartnerSexQueries.deleteTaskDoerPartnerSex(taskId, sexId)
}

fun TasksDatabase.deleteTaskDoerPartnerSexesByTaskId(
	taskId: Long
) {
	taskDoerPartnerSexQueries.deleteTaskDoerPartnerSexesByTaskId(taskId)
}