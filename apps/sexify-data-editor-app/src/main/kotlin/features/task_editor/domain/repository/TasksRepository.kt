package features.task_editor.domain.repository

import com.lauruscorp.sexify_data.entities.SexifyLanguage
import features.task_editor.domain.entities.TaskEditorError
import features.task_editor.domain.entities.TaskEditorTask

interface TasksRepository {
	suspend fun getTaskById(
		id: Long,
		language: SexifyLanguage? = null
	): TaskEditorTask?
	
	suspend fun saveTask(
		task: TaskEditorTask
	): TaskEditorError?
	
	suspend fun deleteTask(
		taskId: Long
	)
}