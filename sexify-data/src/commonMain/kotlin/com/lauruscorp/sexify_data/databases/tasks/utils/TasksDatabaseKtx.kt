package com.lauruscorp.sexify_data.databases.tasks.utils

import com.lauruscorp.sexify_data.databases.tasks.TasksDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun TasksDatabase.selectLastInsertRowId(): Long? {
	return utilsQueries.lastInsertRowId()
		.executeAsOneOrNull()
}

fun TasksDatabase.getText(
	textContentId: Long,
	languageId: String? = null
): String? {
	val translation = if (languageId != null) {
		getTranslatedText(textContentId, languageId)
	} else {
		null
	}
	
	return translation ?: getOriginalText(textContentId)
}

fun TasksDatabase.getTranslatedText(
	textContentId: Long,
	languageId: String
): String? {
	return selectTranslationBy(textContentId, languageId)?.translation
}

fun TasksDatabase.flowGetTranslatedText(
	textContentId: Long,
	languageId: String
): Flow<String?> {
	return flowSelectTranslationBy(textContentId, languageId)
		.map { it?.translation }
}

fun TasksDatabase.getOriginalText(
	textContentId: Long
): String? {
	return selectTextContentById(textContentId)?.originalText
}

fun TasksDatabase.flowGetOriginalText(
	textContentId: Long
): Flow<String?> {
	return flowSelectTextContentById(textContentId)
		.map { it?.originalText }
}