package com.lauruscorp.sexify_data.database.utils

import com.lauruscorp.sexify_data.database.SexifyDatabase
import com.lauruscorp.sexifydata.database.tables.TaskStage
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOneOrNull
import kotlinx.coroutines.flow.Flow

fun TaskStage.getName(
	database: SexifyDatabase,
	languageId: String? = null
): String? {
	return database.getText(textContentId = nameTextId, languageId = languageId)
}

fun TaskStage.getDescription(
	database: SexifyDatabase,
	languageId: String? = null
): String? {
	return descriptionTextId?.let {
		database.getText(textContentId = descriptionTextId, languageId = languageId)
	}
}

fun SexifyDatabase.selectAllTaskStages(): List<TaskStage> {
	return taskStageQueries.selectAllTaskStages()
		.executeAsList()
}

fun SexifyDatabase.flowSelectAllTaskStages(): Flow<List<TaskStage>> {
	return taskStageQueries.selectAllTaskStages()
		.asFlow()
		.mapToList()
}

fun SexifyDatabase.selectTaskStageById(
	id: Long
): TaskStage? {
	return taskStageQueries.selectTaskStageById(id)
		.executeAsOneOrNull()
}

fun SexifyDatabase.flowSelectTaskStageById(
	id: Long
): Flow<TaskStage?> {
	return taskStageQueries.selectTaskStageById(id)
		.asFlow()
		.mapToOneOrNull()
}