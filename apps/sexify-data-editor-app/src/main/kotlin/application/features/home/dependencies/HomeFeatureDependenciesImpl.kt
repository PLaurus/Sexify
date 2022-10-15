package application.features.home.dependencies

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.lauruscorp.core_jvm.coroutines.CoroutineDispatchers
import dagger.Module
import features.home.dependencies.HomeFeatureDependencies
import features.home.domain.repository.TasksRepository
import javax.inject.Inject

@Module
internal class HomeFeatureDependenciesImpl @Inject constructor(
	private val tasksRepository: TasksRepository,
	private val storeFactory: StoreFactory,
	private val coroutinesDispatchers: CoroutineDispatchers
) : HomeFeatureDependencies {
	override fun getStoreFactory(): StoreFactory = storeFactory
	override fun getCoroutineDispatchers(): CoroutineDispatchers = coroutinesDispatchers
	override fun getTasksRepository(): TasksRepository = tasksRepository
}