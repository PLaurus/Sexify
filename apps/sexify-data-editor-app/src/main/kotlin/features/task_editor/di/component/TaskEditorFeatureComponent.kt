package features.task_editor.di.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.lauruscorp.core_jvm.di.dagger.qualifiers.mvi.InitialStateQualifier
import dagger.BindsInstance
import dagger.Component
import features.task_editor.dependencies.TaskEditorFeatureDependencies
import features.task_editor.di.component.qualifiers.TaskEditorTaskIdQualifier
import features.task_editor.di.component.scope.TaskEditorFeatureScope
import features.task_editor.di.modules.res.strings.StringsModule
import features.task_editor.di.modules.store.TaskEditorStoreModule
import features.task_editor.domain.store.TaskEditorStore

@TaskEditorFeatureScope
@Component(
	dependencies = [
		TaskEditorFeatureDependencies::class
	],
	modules = [
		TaskEditorStoreModule::class,
		StringsModule::class
	]
)
internal interface TaskEditorFeatureComponent {
	fun getTaskEditorStore(): TaskEditorStore
	
	@InitialStateQualifier
	fun getTaskEditorStoreInitialState(): TaskEditorStore.State
	
	@Component.Factory
	interface Factory {
		fun create(
			dependencies: TaskEditorFeatureDependencies,
			@BindsInstance @TaskEditorTaskIdQualifier taskEditorTaskId: Long?
		): TaskEditorFeatureComponent
	}
	
	companion object {
		operator fun invoke(
			dependencies: TaskEditorFeatureDependencies,
			taskEditorTaskId: Long?
		): TaskEditorFeatureComponent {
			return DaggerTaskEditorFeatureComponent.factory()
				.create(
					dependencies = dependencies,
					taskEditorTaskId = taskEditorTaskId
				)
		}
	}
}

@Composable
internal fun rememberTaskEditorComponent(
	dependencies: TaskEditorFeatureDependencies,
	taskEditorTaskId: Long?
): TaskEditorFeatureComponent {
	return remember {
		TaskEditorFeatureComponent(dependencies, taskEditorTaskId)
	}
}