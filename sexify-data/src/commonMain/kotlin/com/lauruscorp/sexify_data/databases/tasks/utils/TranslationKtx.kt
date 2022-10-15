package com.lauruscorp.sexify_data.databases.tasks.utils

import com.lauruscorp.sexify_data.databases.tasks.TasksDatabase
import com.lauruscorp.sexifydata.databases.tasks.tables.Language
import com.lauruscorp.sexifydata.databases.tasks.tables.TextContent
import com.lauruscorp.sexifydata.databases.tasks.tables.Translation
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOneOrNull
import kotlinx.coroutines.flow.Flow

fun Translation.getOriginalText(
	database: TasksDatabase
): String? {
	return database.getOriginalText(textContentId)
}

fun Translation.flowGetOriginalText(
	database: TasksDatabase
): Flow<String?> {
	return database.flowGetOriginalText(textContentId)
}

fun Translation.getCorrespondingTextContent(
	database: TasksDatabase
): TextContent? {
	return database.selectTextContentById(textContentId)
}

fun Translation.flowGetCorrespondingTextContent(
	database: TasksDatabase
): Flow<TextContent?> {
	return database.flowSelectTextContentById(textContentId)
}

fun Translation.getLanguage(
	database: TasksDatabase
): Language? {
	return database.selectLanguageById(languageId)
}

fun Translation.flowGetLanguage(
	database: TasksDatabase
): Flow<Language?> {
	return database.flowSelectLanguageById(languageId)
}

fun TasksDatabase.selectAllTranslations(): List<Translation> {
	return translationQueries.selectAllTranslations()
		.executeAsList()
}

fun TasksDatabase.flowSelectAllTranslations(): Flow<List<Translation>> {
	return translationQueries.selectAllTranslations()
		.asFlow()
		.mapToList()
}

fun TasksDatabase.selectTranslationById(
	id: Long
): Translation? {
	return translationQueries.selectTranslationById(id)
		.executeAsOneOrNull()
}

fun TasksDatabase.flowSelectTranslationById(
	id: Long
): Flow<Translation?> {
	return translationQueries.selectTranslationById(id)
		.asFlow()
		.mapToOneOrNull()
}

fun TasksDatabase.selectTranslationsByTextContentId(
	textContentId: Long
): List<Translation> {
	return translationQueries.selectTranslationsByTextContentId(textContentId)
		.executeAsList()
}

fun TasksDatabase.flowSelectTranslationsByTextContentId(
	textContentId: Long
): Flow<List<Translation>> {
	return translationQueries.selectTranslationsByTextContentId(textContentId)
		.asFlow()
		.mapToList()
}

fun TasksDatabase.selectTranslationBy(
	textContentId: Long,
	languageId: String
): Translation? {
	return translationQueries.selectTranslationBy(textContentId, languageId)
		.executeAsOneOrNull()
}

fun TasksDatabase.flowSelectTranslationBy(
	textContentId: Long,
	languageId: String
): Flow<Translation?> {
	return translationQueries.selectTranslationBy(textContentId, languageId)
		.asFlow()
		.mapToOneOrNull()
}

fun TasksDatabase.selectTaskTextTranslationByTaskId(
	taskId: Long
): Translation? {
	return translationQueries.selectTaskTextTranslationByTaskId(taskId)
		.executeAsOneOrNull()
}

fun TasksDatabase.flowSelectTaskTextTranslationByTaskId(
	taskId: Long
): Flow<Translation?> {
	return translationQueries.selectTaskTextTranslationByTaskId(taskId)
		.asFlow()
		.mapToOneOrNull()
}

fun TasksDatabase.insertTranslationFields(
	textContentId: Long,
	languageId: String,
	translation: String
): Long? {
	translationQueries.insertTranslationFields(textContentId, languageId, translation)
	return selectLastInsertRowId()
}

fun TasksDatabase.updateTranslationById(
	id: Long,
	translation: String
) {
	translationQueries.updateTranslationById(translation, id)
}

fun TasksDatabase.updateTranslationBy(
	textContentId: Long,
	languageId: String,
	translation: String
) {
	translationQueries.updateTranslationBy(translation, textContentId, languageId)
}

fun TasksDatabase.deleteTranslationById(
	id: Long
) {
	translationQueries.deleteTranslationById(id)
}

fun TasksDatabase.deleteTranslationsByTextContentId(
	textContentId: Long
) {
	translationQueries.deleteTranslationsByTextContentId(textContentId)
}

fun TasksDatabase.deleteTranslationsBy(
	textContentId: Long,
	languageId: String
) {
	translationQueries.deleteTranslationsBy(textContentId, languageId)
}