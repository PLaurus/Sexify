package com.lauruscorp.sexify_data.database.utils

import com.lauruscorp.sexify_data.database.SexifyDatabase
import com.lauruscorp.sexifydata.database.tables.Language
import com.lauruscorp.sexifydata.database.tables.TextContent
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOneOrNull
import kotlinx.coroutines.flow.Flow

fun TextContent.getOriginalLanguage(
	database: SexifyDatabase
): Language? {
	return database.selectLanguageById(originalLanguageId)
}

fun TextContent.flowGetOriginalLanguage(
	database: SexifyDatabase
): Flow<Language?> {
	return database.flowSelectLanguageById(originalLanguageId)
}

fun SexifyDatabase.selectAllTextContents(): List<TextContent> {
	return textContentQueries.selectAllTextContents()
		.executeAsList()
}

fun SexifyDatabase.flowSelectAllTextContents(): Flow<List<TextContent>> {
	return textContentQueries.selectAllTextContents()
		.asFlow()
		.mapToList()
}

fun SexifyDatabase.selectTextContentById(
	id: Long
): TextContent? {
	return textContentQueries.selectTextContentById(id)
		.executeAsOneOrNull()
}

fun SexifyDatabase.flowSelectTextContentById(
	id: Long
): Flow<TextContent?> {
	return textContentQueries.selectTextContentById(id)
		.asFlow()
		.mapToOneOrNull()
}

/**
 * @return inserted row id
 */
fun SexifyDatabase.insertTextContentFields(
	originalText: String,
	originalLanguageId: String
): Long? {
	textContentQueries.insertTextContentFields(originalText, originalLanguageId)
	return selectLastInsertRowId()
}

fun SexifyDatabase.updateTextContentById(
	id: Long,
	originalText: String,
	originalLanguageId: String
) {
	return textContentQueries.updateTextContentById(originalText, originalLanguageId, id)
}

fun SexifyDatabase.deleteTextContentById(
	id: Long
) {
	return textContentQueries.deleteTextContentById(id)
}