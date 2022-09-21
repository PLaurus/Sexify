package application.features.task_editor.di

import application.features.task_editor.dependencies.TaskEditorFeatureDependenciesImpl
import dagger.Binds
import dagger.Module
import features.task_editor.di.component.dependencies.TaskEditorFeatureDependencies

@Module
internal interface TaskEditorFeatureModule {
	@Binds
	fun provideTaskEditorFeatureDependencies(
		dependencies: TaskEditorFeatureDependenciesImpl
	): TaskEditorFeatureDependencies
}