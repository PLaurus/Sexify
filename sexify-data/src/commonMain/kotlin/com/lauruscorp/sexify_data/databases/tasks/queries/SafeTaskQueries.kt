package com.lauruscorp.sexify_data.databases.tasks.queries

import com.lauruscorp.sexifydata.databases.tasks.tables.TaskQueries
import com.lauruscorp.sexifydata.databases.tasks.tables.TextContentQueries

internal class SafeTaskQueries(
	private val taskQueries: TaskQueries,
	private val textContentQueries: TextContentQueries
) : TaskQueries by taskQueries {
	override fun deleteTaskById(id: Long) {
		taskQueries.transaction body@{
			val task = taskQueries.selectTaskById(id)
				.executeAsOneOrNull() ?: rollback()
			
			taskQueries.deleteTaskById(id)
			
			val textContentId = task.textId
			textContentQueries.deleteTextContentById(textContentId)
		}
	}
}