package com.lauruscorp.sexify_data.databases.tasks.utils

import com.lauruscorp.sexify_data.databases.tasks.TasksDatabase
import com.lauruscorp.sexifydata.databases.tasks.tables.TaskStage
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOneOrNull
import kotlinx.coroutines.flow.Flow

fun TaskStage.getName(
	database: TasksDatabase,
	languageId: String? = null
): String? {
	return database.getText(textContentId = nameTextId, languageId = languageId)
}

fun TaskStage.getDescription(
	database: TasksDatabase,
	languageId: String? = null
): String? {
	return descriptionTextId?.let {
		database.getText(textContentId = descriptionTextId, languageId = languageId)
	}
}

fun TasksDatabase.selectAllTaskStages(): List<TaskStage> {
	return taskStageQueries.selectAllTaskStages()
		.executeAsList()
}

fun TasksDatabase.flowSelectAllTaskStages(): Flow<List<TaskStage>> {
	return taskStageQueries.selectAllTaskStages()
		.asFlow()
		.mapToList()
}

fun TasksDatabase.selectTaskStageById(
	id: Long
): TaskStage? {
	return taskStageQueries.selectTaskStageById(id)
		.executeAsOneOrNull()
}

fun TasksDatabase.flowSelectTaskStageById(
	id: Long
): Flow<TaskStage?> {
	return taskStageQueries.selectTaskStageById(id)
		.asFlow()
		.mapToOneOrNull()
}