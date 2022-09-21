package features.task_editor.di.modules.store

import application.BuildConfig
import com.arkivanov.mvikotlin.core.store.Bootstrapper
import com.arkivanov.mvikotlin.core.store.Executor
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.lauruscorp.core_jvm.di.dagger.qualifiers.mvi.InitialStateQualifier
import com.lauruscorp.core_jvm.di.dagger.qualifiers.mvi.StoreNameQualifier
import dagger.Binds
import dagger.Module
import dagger.Provides
import features.task_editor.di.component.scope.TaskEditorFeatureScope
import features.task_editor.domain.entities.LoadingState
import features.task_editor.domain.store.TaskEditorExecutor
import features.task_editor.domain.store.TaskEditorReducer
import features.task_editor.domain.store.TaskEditorStore
import javax.inject.Provider

@Module
internal abstract class TaskEditorStoreModule {
	companion object {
		@Provides
		@TaskEditorFeatureScope
		fun provideStore(
			storeFactory: StoreFactory,
			@StoreNameQualifier storeName: String,
			@InitialStateQualifier initialState: TaskEditorStore.State,
			bootstrapper: @JvmSuppressWildcards Bootstrapper<TaskEditorStore.Action>,
			executorProvider: @JvmSuppressWildcards Provider<Executor<TaskEditorStore.Intent, TaskEditorStore.Action, TaskEditorStore.State, TaskEditorStore.Message, Any>>,
			reducer: @JvmSuppressWildcards Reducer<TaskEditorStore.State, TaskEditorStore.Message>
		): TaskEditorStore {
			return object : TaskEditorStore,
			                Store<TaskEditorStore.Intent, TaskEditorStore.State, Any> by storeFactory.create(
				                name = storeName,
				                initialState = initialState,
				                bootstrapper = bootstrapper,
				                executorFactory = executorProvider::get,
				                reducer = reducer
			                ) {}
		}
		
		@Provides
		@StoreNameQualifier
		fun provideStoreName(): String {
			return TaskEditorStore::class.java.simpleName
		}
		
		@Provides
		@InitialStateQualifier
		fun provideInitialState(): TaskEditorStore.State {
			return TaskEditorStore.State(
				dataLoadingState = LoadingState.Loading,
				id = null,
				originalText = "",
				originalTextLanguage = BuildConfig.DEFAULT_SEXIFY_LANGUAGE,
				textTranslations = emptyMap(),
				availableStages = emptyList(),
				stage = null,
				availableSexes = emptyList(),
				doerSexes = emptyList(),
				partnerSexes = emptyList(),
				timerSec = null
			)
		}
		
		@Provides
		fun provideTaskEditorBootstrapper(): Bootstrapper<TaskEditorStore.Action> {
			return SimpleBootstrapper(TaskEditorStore.Action.LoadData)
		}
	}
	
	@Binds
	abstract fun provideTaskEditorExecutor(
		executor: TaskEditorExecutor
	): Executor<TaskEditorStore.Intent, TaskEditorStore.Action, TaskEditorStore.State, TaskEditorStore.Message, Any>
	
	@Binds
	abstract fun provideTaskEditorReducer(
		reducer: TaskEditorReducer
	): Reducer<TaskEditorStore.State, TaskEditorStore.Message>
}