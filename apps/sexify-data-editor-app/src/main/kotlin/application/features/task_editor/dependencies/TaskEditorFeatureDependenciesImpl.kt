package application.features.task_editor.dependencies

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.lauruscorp.core_jvm.coroutines.CoroutineDispatchers
import com.lauruscorp.sexify_data.database.SexifyDatabase
import dagger.Module
import features.task_editor.di.component.dependencies.TaskEditorFeatureDependencies
import javax.inject.Inject

@Module
internal class TaskEditorFeatureDependenciesImpl @Inject constructor(
	private val sexifyDatabase: SexifyDatabase,
	private val storeFactory: StoreFactory,
	private val coroutinesDispatchers: CoroutineDispatchers
) : TaskEditorFeatureDependencies {
	override fun getSexifyDatabase(): SexifyDatabase = sexifyDatabase
	
	override fun getStoreFactory(): StoreFactory = storeFactory
	
	override fun getCoroutineDispatchers(): CoroutineDispatchers = coroutinesDispatchers
}