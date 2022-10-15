package application.features.home.data.repository

import application.features.home.data.mapping.asHomeTask
import com.lauruscorp.sexify_data.entities.SexifyLanguage
import features.home.domain.entities.HomeTask
import features.home.domain.repository.TasksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class HomeFeatureTasksRepository @Inject constructor(
    private val appTasksRepository: com.lauruscorp.sexify_data.repositories.tasks.TasksRepository
) : TasksRepository {
    override suspend fun getTasks(language: SexifyLanguage): List<HomeTask> {
        return appTasksRepository.readAll()
            .getOrNull()
            ?.map { it.asHomeTask(language) }
            ?: emptyList()
    }

    override fun getTasksFlow(language: SexifyLanguage): Flow<List<HomeTask>> {
        return appTasksRepository.readAllFlow()
            .map { result ->
                result.getOrNull()
                    ?.map { task -> task.asHomeTask(language) }
                    ?: emptyList()
            }
    }
}