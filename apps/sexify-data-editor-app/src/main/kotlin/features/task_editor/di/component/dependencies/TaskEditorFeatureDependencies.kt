package features.task_editor.di.component.dependencies

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.lauruscorp.core_jvm.coroutines.CoroutineDispatchers
import com.lauruscorp.sexify_data.database.SexifyDatabase

interface TaskEditorFeatureDependencies {
	fun getSexifyDatabase(): SexifyDatabase
	fun getStoreFactory(): StoreFactory
	fun getCoroutineDispatchers(): CoroutineDispatchers
}