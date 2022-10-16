package features.home.dependencies

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.lauruscorp.core.coroutines.CoroutineDispatchers
import features.home.domain.repository.TasksRepository

interface HomeFeatureDependencies {
    fun getStoreFactory(): StoreFactory
    fun getCoroutineDispatchers(): CoroutineDispatchers
    fun getTasksRepository(): TasksRepository
}