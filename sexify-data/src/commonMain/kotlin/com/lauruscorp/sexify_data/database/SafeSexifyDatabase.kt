package com.lauruscorp.sexify_data.database

import com.lauruscorp.sexify_data.database.queries.SafeTaskQueries
import com.lauruscorp.sexify_data.database.queries.SafeTextContentQueries
import com.lauruscorp.sexifydata.database.tables.TaskQueries
import com.lauruscorp.sexifydata.database.tables.TextContentQueries
import com.squareup.sqldelight.db.SqlDriver

class SafeSexifyDatabase private constructor(
	sexifyDatabase: SexifyDatabase
) : SexifyDatabase by sexifyDatabase {
	
	override val textContentQueries: TextContentQueries = SafeTextContentQueries(
		textContentQueries = sexifyDatabase.textContentQueries,
		translationQueries = translationQueries
	)
	
	override val taskQueries: TaskQueries = SafeTaskQueries(
		taskQueries = sexifyDatabase.taskQueries,
		textContentQueries = textContentQueries
	)
	
	companion object {
		operator fun invoke(
			driver: SqlDriver
		): SexifyDatabase {
			return SexifyDatabase(driver)
		}
	}
}