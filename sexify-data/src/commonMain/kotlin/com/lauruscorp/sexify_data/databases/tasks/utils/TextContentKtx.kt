package com.lauruscorp.sexify_data.databases.tasks.utils

import com.lauruscorp.sexify_data.databases.tasks.TasksDatabase
import com.lauruscorp.sexifydata.databases.tasks.tables.Language
import com.lauruscorp.sexifydata.databases.tasks.tables.TextContent
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOneOrNull
import kotlinx.coroutines.flow.Flow

fun TextContent.getOriginalLanguage(
	database: TasksDatabase
): Language? {
	return database.selectLanguageById(originalLanguageId)
}

fun TextContent.flowGetOriginalLanguage(
	database: TasksDatabase
): Flow<Language?> {
	return database.flowSelectLanguageById(originalLanguageId)
}

fun TasksDatabase.selectAllTextContents(): List<TextContent> {
	return textContentQueries.selectAllTextContents()
		.executeAsList()
}

fun TasksDatabase.flowSelectAllTextContents(): Flow<List<TextContent>> {
	return textContentQueries.selectAllTextContents()
		.asFlow()
		.mapToList()
}

fun TasksDatabase.selectTextContentById(
	id: Long
): TextContent? {
	return textContentQueries.selectTextContentById(id)
		.executeAsOneOrNull()
}

fun TasksDatabase.flowSelectTextContentById(
	id: Long
): Flow<TextContent?> {
	return textContentQueries.selectTextContentById(id)
		.asFlow()
		.mapToOneOrNull()
}

/**
 * @return inserted row id
 */
fun TasksDatabase.insertTextContentFields(
	originalText: String,
	originalLanguageId: String
): Long? {
	textContentQueries.insertTextContentFields(originalText, originalLanguageId)
	return selectLastInsertRowId()
}

fun TasksDatabase.updateTextContentById(
	id: Long,
	originalText: String,
	originalLanguageId: String
) {
	return textContentQueries.updateTextContentById(originalText, originalLanguageId, id)
}

fun TasksDatabase.deleteTextContentById(
	id: Long
) {
	return textContentQueries.deleteTextContentById(id)
}