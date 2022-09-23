package features.task_editor.domain.repository

import com.lauruscorp.sexify_data.languages.SexifyLanguage
import features.task_editor.domain.entities.Task
import features.task_editor.domain.entities.TaskEditorError

interface TasksRepository {
	suspend fun getTaskById(
		id: Long,
		language: SexifyLanguage? = null
	): Task?
	
	suspend fun saveTask(
		task: Task
	): TaskEditorError?
	
	suspend fun deleteTask(
		taskId: Long
	)
}