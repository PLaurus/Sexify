package application.features.task_editor.data.repository

import application.features.task_editor.data.mapping.asTaskEditorTaskStage
import com.lauruscorp.sexify_data.entities.SexifyLanguage
import features.task_editor.domain.entities.TaskEditorTask
import features.task_editor.domain.repository.TaskStagesRepository
import javax.inject.Inject

internal class TaskEditorFeatureTaskStagesRepository @Inject constructor(
	private val appTaskStagesRepository: com.lauruscorp.sexify_data.repositories.task_stages.TaskStagesRepository,
) : TaskStagesRepository {
	override suspend fun getAllTaskStages(
		language: SexifyLanguage?
	): List<TaskEditorTask.Stage> {
		return appTaskStagesRepository.getAll()
			.map { it.asTaskEditorTaskStage(language) }
	}
}