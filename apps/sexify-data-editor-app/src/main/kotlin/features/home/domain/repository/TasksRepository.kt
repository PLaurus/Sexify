package features.home.domain.repository

import features.home.domain.entities.Task
import kotlinx.coroutines.flow.Flow

interface TasksRepository {
	suspend fun getTasks(languageId: String): List<Task>
	fun getTasksFlow(languageId: String): Flow<List<Task>>
}