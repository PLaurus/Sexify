package com.lauruscorp.sexify_data.database.utils

import com.lauruscorp.sexify_data.database.SexifyDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun SexifyDatabase.selectLastInsertRowId(): Long? {
	return utilsQueries.lastInsertRowId()
		.executeAsOneOrNull()
}

fun SexifyDatabase.getText(
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

fun SexifyDatabase.getTranslatedText(
	textContentId: Long,
	languageId: String
): String? {
	return selectTranslationBy(textContentId, languageId)?.translation
}

fun SexifyDatabase.flowGetTranslatedText(
	textContentId: Long,
	languageId: String
): Flow<String?> {
	return flowSelectTranslationBy(textContentId, languageId)
		.map { it?.translation }
}

fun SexifyDatabase.getOriginalText(
	textContentId: Long
): String? {
	return selectTextContentById(textContentId)?.originalText
}

fun SexifyDatabase.flowGetOriginalText(
	textContentId: Long
): Flow<String?> {
	return flowSelectTextContentById(textContentId)
		.map { it?.originalText }
}