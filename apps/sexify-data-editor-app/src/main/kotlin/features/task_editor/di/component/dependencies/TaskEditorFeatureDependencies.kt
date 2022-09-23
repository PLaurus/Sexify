package features.task_editor.di.component.dependencies

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.lauruscorp.core_jvm.coroutines.CoroutineDispatchers
import com.lauruscorp.sexify_data.database.SexifyDatabase
import features.task_editor.domain.repository.LanguagesRepository
import features.task_editor.domain.repository.SexesRepository
import features.task_editor.domain.repository.TaskStagesRepository
import features.task_editor.domain.repository.TasksRepository

interface TaskEditorFeatureDependencies {
	fun getSexifyDatabase(): SexifyDatabase
	fun getStoreFactory(): StoreFactory
	fun getCoroutineDispatchers(): CoroutineDispatchers
	fun getTasksRepository(): TasksRepository
	fun getLanguagesRepository(): LanguagesRepository
	fun getTaskStagesRepository(): TaskStagesRepository
	fun getSexesRepository(): SexesRepository
}