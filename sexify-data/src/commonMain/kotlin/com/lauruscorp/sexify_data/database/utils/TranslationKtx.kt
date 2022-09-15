package com.lauruscorp.sexify_data.database.utils

import com.lauruscorp.sexify_data.database.SexifyDatabase
import com.lauruscorp.sexifydata.database.tables.Language
import com.lauruscorp.sexifydata.database.tables.TextContent
import com.lauruscorp.sexifydata.database.tables.Translation
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOneOrNull
import kotlinx.coroutines.flow.Flow

fun Translation.getOriginalText(
	database: SexifyDatabase
): String? {
	return database.getOriginalText(textContentId)
}

fun Translation.flowGetOriginalText(
	database: SexifyDatabase
): Flow<String?> {
	return database.flowGetOriginalText(textContentId)
}

fun Translation.getCorrespondingTextContent(
	database: SexifyDatabase
): TextContent? {
	return database.selectTextContentById(textContentId)
}

fun Translation.flowGetCorrespondingTextContent(
	database: SexifyDatabase
): Flow<TextContent?> {
	return database.flowSelectTextContentById(textContentId)
}

fun Translation.getLanguage(
	database: SexifyDatabase
): Language? {
	return database.selectLanguageById(languageId)
}

fun Translation.flowGetLanguage(
	database: SexifyDatabase
): Flow<Language?> {
	return database.flowSelectLanguageById(languageId)
}

fun SexifyDatabase.selectAllTranslations(): List<Translation> {
	return translationQueries.selectAllTranslations()
		.executeAsList()
}

fun SexifyDatabase.flowSelectAllTranslations(): Flow<List<Translation>> {
	return translationQueries.selectAllTranslations()
		.asFlow()
		.mapToList()
}

fun SexifyDatabase.selectTranslationById(
	id: Long
): Translation? {
	return translationQueries.selectTranslationById(id)
		.executeAsOneOrNull()
}

fun SexifyDatabase.flowSelectTranslationById(
	id: Long
): Flow<Translation?> {
	return translationQueries.selectTranslationById(id)
		.asFlow()
		.mapToOneOrNull()
}

fun SexifyDatabase.selectTranslationsByTextContentId(
	textContentId: Long
): List<Translation> {
	return translationQueries.selectTranslationsByTextContentId(textContentId)
		.executeAsList()
}

fun SexifyDatabase.flowSelectTranslationsByTextContentId(
	textContentId: Long
): Flow<List<Translation>> {
	return translationQueries.selectTranslationsByTextContentId(textContentId)
		.asFlow()
		.mapToList()
}

fun SexifyDatabase.selectTranslationBy(
	textContentId: Long,
	languageId: String
): Translation? {
	return translationQueries.selectTranslationBy(textContentId, languageId)
		.executeAsOneOrNull()
}

fun SexifyDatabase.flowSelectTranslationBy(
	textContentId: Long,
	languageId: String
): Flow<Translation?> {
	return translationQueries.selectTranslationBy(textContentId, languageId)
		.asFlow()
		.mapToOneOrNull()
}

fun SexifyDatabase.selectTaskTextTranslationByTaskId(
	taskId: Long
): Translation? {
	return translationQueries.selectTaskTextTranslationByTaskId(taskId)
		.executeAsOneOrNull()
}

fun SexifyDatabase.flowSelectTaskTextTranslationByTaskId(
	taskId: Long
): Flow<Translation?> {
	return translationQueries.selectTaskTextTranslationByTaskId(taskId)
		.asFlow()
		.mapToOneOrNull()
}

fun SexifyDatabase.insertTranslationFields(
	textContentId: Long,
	languageId: String,
	translation: String
) {
	translationQueries.insertTranslationFields(textContentId, languageId, translation)
}

fun SexifyDatabase.updateTranslationById(
	id: Long,
	translation: String
) {
	translationQueries.updateTranslationById(translation, id)
}

fun SexifyDatabase.updateTranslationBy(
	textContentId: Long,
	languageId: String,
	translation: String
) {
	translationQueries.updateTranslationBy(translation, textContentId, languageId)
}

fun SexifyDatabase.deleteTranslationById(
	id: Long
) {
	translationQueries.deleteTranslationById(id)
}

fun SexifyDatabase.deleteTranslationsByTextContentId(
	textContentId: Long
) {
	translationQueries.deleteTranslationsByTextContentId(textContentId)
}

fun SexifyDatabase.deleteTranslationsBy(
	textContentId: Long,
	languageId: String
) {
	translationQueries.deleteTranslationsBy(textContentId, languageId)
}