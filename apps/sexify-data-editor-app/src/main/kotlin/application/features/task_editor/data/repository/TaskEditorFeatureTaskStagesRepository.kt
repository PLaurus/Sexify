package application.features.task_editor.data.repository

import application.features.task_editor.data.mapping.toTaskEditorFeatureTaskStage
import com.lauruscorp.core_jvm.coroutines.CoroutineDispatchers
import com.lauruscorp.sexify_data.database.SexifyDatabase
import com.lauruscorp.sexify_data.database.utils.selectAllTaskStages
import com.lauruscorp.sexify_data.languages.SexifyLanguage
import features.task_editor.domain.entities.Task
import features.task_editor.domain.repository.TaskStagesRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class TaskEditorFeatureTaskStagesRepository @Inject constructor(
	private val coroutineDispatchers: CoroutineDispatchers,
	private val database: SexifyDatabase
) : TaskStagesRepository {
	override suspend fun getAllTaskStages(
		language: SexifyLanguage?
	): List<Task.Stage> = withContext(coroutineDispatchers.io) {
		database.transactionWithResult {
			database.selectAllTaskStages()
				.mapNotNull { dbTaskStage ->
					dbTaskStage.toTaskEditorFeatureTaskStage(
						database = database,
						languageTag = language?.tag
					)
				}
		}
	}
}