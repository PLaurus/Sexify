package features.task_editor.domain.repository

import com.lauruscorp.sexify_data.languages.SexifyLanguage
import features.task_editor.domain.entities.Task

interface TaskStagesRepository {
	suspend fun getAllTaskStages(language: SexifyLanguage? = null): List<Task.Stage>
}