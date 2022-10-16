package features.task_editor.dependencies

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.lauruscorp.core.coroutines.CoroutineDispatchers
import features.task_editor.domain.repository.LanguagesRepository
import features.task_editor.domain.repository.SexesRepository
import features.task_editor.domain.repository.TaskStagesRepository
import features.task_editor.domain.repository.TasksRepository

interface TaskEditorFeatureDependencies {
	fun getStoreFactory(): StoreFactory
	fun getCoroutineDispatchers(): CoroutineDispatchers
	fun getTasksRepository(): TasksRepository
	fun getLanguagesRepository(): LanguagesRepository
	fun getTaskStagesRepository(): TaskStagesRepository
	fun getSexesRepository(): SexesRepository
}