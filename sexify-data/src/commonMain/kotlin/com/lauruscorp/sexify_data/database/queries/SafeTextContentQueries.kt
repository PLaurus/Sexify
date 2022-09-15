package com.lauruscorp.sexify_data.database.queries

import com.lauruscorp.sexifydata.database.tables.TextContentQueries
import com.lauruscorp.sexifydata.database.tables.TranslationQueries

class SafeTextContentQueries(
	private val textContentQueries: TextContentQueries,
	private val translationQueries: TranslationQueries
) : TextContentQueries by textContentQueries {
	override fun deleteTextContentById(id: Long) {
		textContentQueries.transaction {
			translationQueries.deleteTranslationsByTextContentId(id)
			textContentQueries.deleteTextContentById(id)
		}
	}
}