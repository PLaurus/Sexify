package com.lauruscorp.sexify_data.database.utils

import com.lauruscorp.sexify_data.database.SexifyDatabase
import com.lauruscorp.sexifydata.database.tables.Sex
import com.lauruscorp.sexifydata.database.tables.Task
import com.lauruscorp.sexifydata.database.tables.TaskDoerPartnerSex
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow

fun TaskDoerPartnerSex.getTask(
	database: SexifyDatabase
): Task? {
	return database.selectTaskById(taskId)
}

fun TaskDoerPartnerSex.flowGetTask(
	database: SexifyDatabase
): Flow<Task?> {
	return database.flowSelectTaskById(taskId)
}

fun TaskDoerPartnerSex.getSex(
	database: SexifyDatabase
): Sex? {
	return database.selectSexById(sexId)
}

fun TaskDoerPartnerSex.flowGetSex(
	database: SexifyDatabase
): Flow<Sex?> {
	return database.flowSelectSexById(sexId)
}

fun SexifyDatabase.selectTaskDoerPartnerSexesByTaskId(
	taskId: Long
): List<TaskDoerPartnerSex> {
	return taskDoerPartnerSexQueries.selectTaskDoerPartnerSexesByTaskId(taskId)
		.executeAsList()
}

fun SexifyDatabase.flowSelectTaskDoerPartnerSexesByTaskId(
	taskId: Long
): Flow<List<TaskDoerPartnerSex>> {
	return taskDoerPartnerSexQueries.selectTaskDoerPartnerSexesByTaskId(taskId)
		.asFlow()
		.mapToList()
}

fun SexifyDatabase.insertTaskDoerPartnerSex(
	sex: TaskDoerPartnerSex
): Long? {
	taskDoerPartnerSexQueries.insertTaskDoerPartnerSex(sex)
	return selectLastInsertRowId()
}

fun SexifyDatabase.insertTaskDoerPartnerSexFields(
	taskId: Long,
	sexId: Long
): Long? {
	taskDoerPartnerSexQueries.insertTaskDoerPartnerSexFields(taskId, sexId)
	return selectLastInsertRowId()
}

fun SexifyDatabase.deleteTaskDoerPartnerSex(
	taskId: Long,
	sexId: Long
) {
	taskDoerPartnerSexQueries.deleteTaskDoerPartnerSex(taskId, sexId)
}

fun SexifyDatabase.deleteTaskDoerPartnerSexesByTaskId(
	taskId: Long
) {
	taskDoerPartnerSexQueries.deleteTaskDoerPartnerSexesByTaskId(taskId)
}