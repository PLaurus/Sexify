package application.features.task_editor.dependencies

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.lauruscorp.core.coroutines.CoroutineDispatchers
import dagger.Module
import features.task_editor.dependencies.TaskEditorFeatureDependencies
import features.task_editor.domain.repository.LanguagesRepository
import features.task_editor.domain.repository.SexesRepository
import features.task_editor.domain.repository.TaskStagesRepository
import features.task_editor.domain.repository.TasksRepository
import javax.inject.Inject

@Module
internal class TaskEditorFeatureDependenciesImpl @Inject constructor(
	private val storeFactory: StoreFactory,
	private val coroutinesDispatchers: CoroutineDispatchers,
	private val tasksRepository: TasksRepository,
	private val languagesRepository: LanguagesRepository,
	private val taskStagesRepository: TaskStagesRepository,
	private val sexesRepository: SexesRepository
) : TaskEditorFeatureDependencies {
	override fun getStoreFactory(): StoreFactory = storeFactory
	override fun getCoroutineDispatchers(): CoroutineDispatchers = coroutinesDispatchers
	override fun getTasksRepository(): TasksRepository = tasksRepository
	override fun getLanguagesRepository(): LanguagesRepository = languagesRepository
	override fun getTaskStagesRepository(): TaskStagesRepository = taskStagesRepository
	override fun getSexesRepository(): SexesRepository = sexesRepository
}