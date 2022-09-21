package features.home.domain.repository

import features.home.domain.entities.HomeTask
import kotlinx.coroutines.flow.Flow

interface TasksRepository {
	suspend fun getTasks(languageId: String): List<HomeTask>
	fun getTasksFlow(languageId: String): Flow<List<HomeTask>>
}