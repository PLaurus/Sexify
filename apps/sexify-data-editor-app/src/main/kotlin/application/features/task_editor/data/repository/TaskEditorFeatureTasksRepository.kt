package application.features.task_editor.data.repository

import application.features.task_editor.data.mapping.asTaskEditorError
import application.features.task_editor.data.mapping.asTaskEditorTask
import com.lauruscorp.core.util.fold
import com.lauruscorp.core_jvm.coroutines.CoroutineDispatchers
import com.lauruscorp.sexify_data.entities.SexifyLanguage
import com.lauruscorp.sexify_data.entities.Text
import com.lauruscorp.sexify_data.entities.errors.TasksDataError
import features.task_editor.domain.entities.TaskEditorError
import features.task_editor.domain.entities.TaskEditorTask
import features.task_editor.domain.repository.TasksRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class TaskEditorFeatureTasksRepository @Inject constructor(
	private val appTasksRepository: com.lauruscorp.sexify_data.repositories.tasks.TasksRepository,
	private val coroutineDispatchers: CoroutineDispatchers
) : TasksRepository {
	override suspend fun getTaskById(
		id: Long,
		language: SexifyLanguage?
	): TaskEditorTask? {
		return appTasksRepository.read(id)
			.getOrNull()
			?.asTaskEditorTask()
	}

	override suspend fun saveTask(
		task: TaskEditorTask
	): TaskEditorError? = withContext(coroutineDispatchers.default) {
		val stageId = task.stage?.id
			?: return@withContext TaskEditorError.TaskStageError.NotSelected

		val existingDbTask = if (task.id != null) {
			appTasksRepository.read(task.id).getOrNull()
		} else {
			null
		}

		val result = if (existingDbTask != null) {
			appTasksRepository.update(
				id = existingDbTask.id,
				text = Text(
					language = task.originalTextLanguage,
					value = task.originalText
				),
				textTranslations = task.textTranslations
					.mapNotNull { (language, translation) ->
						Text(language, translation)
					},
				stageId = stageId,
				doerSexes = task.doerSexes,
				partnerSexes = task.partnerSexes,
				timerSec = task.timerSec
			)
		} else {
			appTasksRepository.create(
				text = Text(
					language = task.originalTextLanguage,
					value = task.originalText
				),
				textTranslations = task.textTranslations
					.mapNotNull { (language, translation) ->
						Text(language, translation)
					},
				stageId = stageId,
				doerSexes = task.doerSexes,
				partnerSexes = task.partnerSexes,
				timerSec = task.timerSec
			)
		}

		result.fold(
			onSuccess = { null },
			onFailure = { error: TasksDataError -> error.asTaskEditorError() }
		)
	}

	override suspend fun deleteTask(taskId: Long) {
		appTasksRepository.delete(taskId)
	}
}