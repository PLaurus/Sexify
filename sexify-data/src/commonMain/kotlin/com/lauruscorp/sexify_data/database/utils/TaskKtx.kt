package com.lauruscorp.sexify_data.database.utils

import com.lauruscorp.sexify_data.database.SexifyDatabase
import com.lauruscorp.sexifydata.database.tables.Task
import com.lauruscorp.sexifydata.database.tables.TaskDoerPartnerSex
import com.lauruscorp.sexifydata.database.tables.TaskDoerSex
import com.lauruscorp.sexifydata.database.tables.TaskStage
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOneOrNull
import kotlinx.coroutines.flow.Flow

fun Task.getText(
	database: SexifyDatabase,
	languageId: String? = null
): String? {
	return database.getText(textContentId = textId, languageId = languageId)
}

fun Task.getTaskStage(
	database: SexifyDatabase
): TaskStage? {
	return database.selectTaskStageById(taskStageId)
}

fun Task.flowGetTaskStage(
	database: SexifyDatabase
): Flow<TaskStage?> {
	return database.flowSelectTaskStageById(taskStageId)
}

fun Task.getDoerSexes(
	database: SexifyDatabase
): List<TaskDoerSex> {
	return database.selectTaskDoerSexesByTaskId(id)
}

fun Task.flowGetDoerSexes(
	database: SexifyDatabase
): Flow<List<TaskDoerSex>> {
	return database.flowSelectTaskDoerSexesByTaskId(id)
}

fun Task.getDoerPartnerSexes(
	database: SexifyDatabase
): List<TaskDoerPartnerSex> {
	return database.selectTaskDoerPartnerSexesByTaskId(id)
}

fun Task.flowGetDoerPartnerSexes(
	database: SexifyDatabase
): Flow<List<TaskDoerPartnerSex>> {
	return database.flowSelectTaskDoerPartnerSexesByTaskId(id)
}

fun SexifyDatabase.selectAllTasks(): List<Task> {
	return taskQueries.selectAllTasks()
		.executeAsList()
}

fun SexifyDatabase.flowSelectAllTasks(): Flow<List<Task>> {
	return taskQueries.selectAllTasks()
		.asFlow()
		.mapToList()
}

fun SexifyDatabase.selectTaskById(
	id: Long
): Task? {
	return taskQueries.selectTaskById(id)
		.executeAsOneOrNull()
}

fun SexifyDatabase.flowSelectTaskById(
	id: Long
): Flow<Task?> {
	return taskQueries.selectTaskById(id)
		.asFlow()
		.mapToOneOrNull()
}

fun SexifyDatabase.selectTasksByStageId(
	stageId: Long
): List<Task> {
	return taskQueries.selectTasksByStageId(stageId)
		.executeAsList()
}

fun SexifyDatabase.flowSelectTasksByStageId(
	stageId: Long
): Flow<List<Task>> {
	return taskQueries.selectTasksByStageId(stageId)
		.asFlow()
		.mapToList()
}

fun SexifyDatabase.selectTasksBy(
	taskStageId: Long,
	taskDoerSexId: Long,
	taskDoerPartnerSexId: Long
): List<Task> {
	return taskQueries.selectTasksBy(taskStageId, taskDoerSexId, taskDoerPartnerSexId)
		.executeAsList()
}

fun SexifyDatabase.flowSelectTasksBy(
	taskStageId: Long,
	taskDoerSexId: Long,
	taskDoerPartnerSexId: Long
): Flow<List<Task>> {
	return taskQueries.selectTasksBy(taskStageId, taskDoerSexId, taskDoerPartnerSexId)
		.asFlow()
		.mapToList()
}

fun SexifyDatabase.selectTasksByTaskDoerSex(
	taskDoerSexId: Long
): List<Task> {
	return taskQueries.selectTasksByTaskDoerSex(taskDoerSexId)
		.executeAsList()
}

fun SexifyDatabase.flowSelectTasksByTaskDoerSex(
	taskDoerSexId: Long
): Flow<List<Task>> {
	return taskQueries.selectTasksByTaskDoerSex(taskDoerSexId)
		.asFlow()
		.mapToList()
}

fun SexifyDatabase.flowSelectTasksByTaskDoerPartnerSex(
	taskDoerPartnerSexId: Long
): Flow<List<Task>> {
	return taskQueries.selectTasksByTaskDoerPartnerSex(taskDoerPartnerSexId)
		.asFlow()
		.mapToList()
}

fun SexifyDatabase.insertTask(
	task: Task
): Long? {
	taskQueries.insertTask(task)
	return selectLastInsertRowId()
}

fun SexifyDatabase.insertTaskFields(
	textId: Long,
	taskStageId: Long,
	timer: Int? = null
): Long? {
	taskQueries.insertTaskFields(textId, taskStageId, timer)
	return selectLastInsertRowId()
}

fun SexifyDatabase.updateTaskById(
	id: Long,
	textId: Long,
	taskStageId: Long,
	timer: Int? = null
) {
	taskQueries.updateTaskById(textId, taskStageId, timer, id)
}

fun SexifyDatabase.deleteTaskById(
	id: Long
) {
	taskQueries.deleteTaskById(id)
}