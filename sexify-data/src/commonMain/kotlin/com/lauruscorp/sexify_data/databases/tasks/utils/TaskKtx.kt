package com.lauruscorp.sexify_data.databases.tasks.utils

import com.lauruscorp.sexify_data.databases.tasks.TasksDatabase
import com.lauruscorp.sexifydata.databases.tasks.tables.Task
import com.lauruscorp.sexifydata.databases.tasks.tables.TaskDoerPartnerSex
import com.lauruscorp.sexifydata.databases.tasks.tables.TaskDoerSex
import com.lauruscorp.sexifydata.databases.tasks.tables.TaskStage
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOneOrNull
import kotlinx.coroutines.flow.Flow

fun Task.getText(
	database: TasksDatabase,
	languageId: String? = null
): String? {
	return database.getText(textContentId = textId, languageId = languageId)
}

fun Task.getTaskStage(
	database: TasksDatabase
): TaskStage? {
	return database.selectTaskStageById(taskStageId)
}

fun Task.flowGetTaskStage(
	database: TasksDatabase
): Flow<TaskStage?> {
	return database.flowSelectTaskStageById(taskStageId)
}

fun Task.getDoerSexes(
	database: TasksDatabase
): List<TaskDoerSex> {
	return database.selectTaskDoerSexesByTaskId(id)
}

fun Task.flowGetDoerSexes(
	database: TasksDatabase
): Flow<List<TaskDoerSex>> {
	return database.flowSelectTaskDoerSexesByTaskId(id)
}

fun Task.getDoerPartnerSexes(
	database: TasksDatabase
): List<TaskDoerPartnerSex> {
	return database.selectTaskDoerPartnerSexesByTaskId(id)
}

fun Task.flowGetDoerPartnerSexes(
	database: TasksDatabase
): Flow<List<TaskDoerPartnerSex>> {
	return database.flowSelectTaskDoerPartnerSexesByTaskId(id)
}

fun TasksDatabase.selectAllTasks(): List<Task> {
	return taskQueries.selectAllTasks()
		.executeAsList()
}

fun TasksDatabase.flowSelectAllTasks(): Flow<List<Task>> {
	return taskQueries.selectAllTasks()
		.asFlow()
		.mapToList()
}

fun TasksDatabase.selectTaskById(
	id: Long
): Task? {
	return taskQueries.selectTaskById(id)
		.executeAsOneOrNull()
}

fun TasksDatabase.flowSelectTaskById(
	id: Long
): Flow<Task?> {
	return taskQueries.selectTaskById(id)
		.asFlow()
		.mapToOneOrNull()
}

fun TasksDatabase.selectTasksByStageId(
	stageId: Long
): List<Task> {
	return taskQueries.selectTasksByStageId(stageId)
		.executeAsList()
}

fun TasksDatabase.flowSelectTasksByStageId(
	stageId: Long
): Flow<List<Task>> {
	return taskQueries.selectTasksByStageId(stageId)
		.asFlow()
		.mapToList()
}

fun TasksDatabase.selectTasksBy(
	taskStageId: Long,
	taskDoerSexId: Long,
	taskDoerPartnerSexId: Long
): List<Task> {
	return taskQueries.selectTasksBy(taskStageId, taskDoerSexId, taskDoerPartnerSexId)
		.executeAsList()
}

fun TasksDatabase.flowSelectTasksBy(
	taskStageId: Long,
	taskDoerSexId: Long,
	taskDoerPartnerSexId: Long
): Flow<List<Task>> {
	return taskQueries.selectTasksBy(taskStageId, taskDoerSexId, taskDoerPartnerSexId)
		.asFlow()
		.mapToList()
}

fun TasksDatabase.selectTasksByTaskDoerSex(
	taskDoerSexId: Long
): List<Task> {
	return taskQueries.selectTasksByTaskDoerSex(taskDoerSexId)
		.executeAsList()
}

fun TasksDatabase.flowSelectTasksByTaskDoerSex(
	taskDoerSexId: Long
): Flow<List<Task>> {
	return taskQueries.selectTasksByTaskDoerSex(taskDoerSexId)
		.asFlow()
		.mapToList()
}

fun TasksDatabase.flowSelectTasksByTaskDoerPartnerSex(
	taskDoerPartnerSexId: Long
): Flow<List<Task>> {
	return taskQueries.selectTasksByTaskDoerPartnerSex(taskDoerPartnerSexId)
		.asFlow()
		.mapToList()
}

fun TasksDatabase.insertTask(
	task: Task
): Long? {
	taskQueries.insertTask(task)
	return selectLastInsertRowId()
}

fun TasksDatabase.insertTaskFields(
	textId: Long,
	taskStageId: Long,
	timer: Int? = null
): Long? {
	taskQueries.insertTaskFields(textId, taskStageId, timer)
	return selectLastInsertRowId()
}

fun TasksDatabase.updateTaskById(
	id: Long,
	textId: Long,
	taskStageId: Long,
	timer: Int? = null
) {
	taskQueries.updateTaskById(textId, taskStageId, timer, id)
}

fun TasksDatabase.deleteTaskById(
	id: Long
) {
	taskQueries.deleteTaskById(id)
}