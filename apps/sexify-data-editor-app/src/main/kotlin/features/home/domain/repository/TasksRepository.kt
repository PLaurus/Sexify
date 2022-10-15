package features.home.domain.repository

import com.lauruscorp.sexify_data.entities.SexifyLanguage
import features.home.domain.entities.HomeTask
import kotlinx.coroutines.flow.Flow

interface TasksRepository {
	suspend fun getTasks(language: SexifyLanguage): List<HomeTask>
	fun getTasksFlow(language: SexifyLanguage): Flow<List<HomeTask>>
}