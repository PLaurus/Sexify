package features.task_editor.domain.repository

import com.lauruscorp.sexify_data.entities.SexifyLanguage
import features.task_editor.domain.entities.TaskEditorTask

interface TaskStagesRepository {
	suspend fun getAllTaskStages(language: SexifyLanguage? = null): List<TaskEditorTask.Stage>
}