package application.features.task_editor.di

import application.features.task_editor.data.repository.TaskEditorFeatureLanguagesRepository
import application.features.task_editor.data.repository.TaskEditorFeatureSexesRepository
import application.features.task_editor.data.repository.TaskEditorFeatureTaskStagesRepository
import application.features.task_editor.data.repository.TaskEditorFeatureTasksRepository
import application.features.task_editor.dependencies.TaskEditorFeatureDependenciesImpl
import dagger.Binds
import dagger.Module
import features.task_editor.di.component.dependencies.TaskEditorFeatureDependencies
import features.task_editor.domain.repository.LanguagesRepository
import features.task_editor.domain.repository.SexesRepository
import features.task_editor.domain.repository.TaskStagesRepository
import features.task_editor.domain.repository.TasksRepository

@Module
internal interface TaskEditorFeatureModule {
	@Binds
	fun provideTaskEditorFeatureDependencies(
		dependencies: TaskEditorFeatureDependenciesImpl
	): TaskEditorFeatureDependencies
	
	@Binds
	fun provideTaskRepository(
		repository: TaskEditorFeatureTasksRepository
	): TasksRepository
	
	@Binds
	fun provideLanguagesRepository(
		repository: TaskEditorFeatureLanguagesRepository
	): LanguagesRepository
	
	@Binds
	fun provideTaskStagesRepository(
		repository: TaskEditorFeatureTaskStagesRepository
	): TaskStagesRepository
	
	@Binds
	fun provideSexesRepository(
		repository: TaskEditorFeatureSexesRepository
	): SexesRepository
}