package com.lauruscorp.sexify_data.databases.tasks.queries

import com.lauruscorp.sexifydata.databases.tasks.tables.TextContentQueries
import com.lauruscorp.sexifydata.databases.tasks.tables.TranslationQueries

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