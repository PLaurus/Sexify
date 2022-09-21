package features.task_editor.di.modules.repository

import dagger.Binds
import dagger.Module
import features.task_editor.data.repository.LanguagesRepositoryImpl
import features.task_editor.data.repository.TasksRepositoryImpl
import features.task_editor.domain.repository.LanguagesRepository
import features.task_editor.domain.repository.TasksRepository

@Module
internal interface RepositoryModule {
	@Binds
	fun provideTaskRepository(
		repository: TasksRepositoryImpl
	): TasksRepository
	
	@Binds
	fun provideLanguagesRepository(
		repository: LanguagesRepositoryImpl
	): LanguagesRepository
}