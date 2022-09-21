package features.task_editor.domain.repository

import com.lauruscorp.sexify_data.languages.SexifyLanguage
import features.task_editor.domain.entities.Task

interface TasksRepository {
	suspend fun getTaskById(
		id: Long,
		language: SexifyLanguage? = null
	): Task?
	
	suspend fun saveTask(task: Task) {
	}
}