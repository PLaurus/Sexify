package application.features.task_editor.data.mapping

import com.lauruscorp.sexify_data.database.SexifyDatabase
import com.lauruscorp.sexify_data.database.aliases.DbTaskStage
import com.lauruscorp.sexify_data.database.utils.getDescription
import com.lauruscorp.sexify_data.database.utils.getName
import features.task_editor.domain.entities.Task

fun DbTaskStage.toTaskEditorFeatureTaskStage(
	database: SexifyDatabase,
	languageTag: String? = null
): Task.Stage? {
	return Task.Stage(
		id = id,
		name = getName(database, languageTag) ?: return null,
		description = getDescription(database, languageTag)
	)
}