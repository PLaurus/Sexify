package features.home.di.modules.repository

import dagger.Binds
import dagger.Module
import features.home.data.repository.TasksRepositoryImpl
import features.home.di.component.scope.HomeFeatureScope
import features.home.domain.repository.TasksRepository

@Module
internal interface HomeRepositoryModule {
	@HomeFeatureScope
	@Binds
	fun provideTasksRepository(
		repository: TasksRepositoryImpl
	): TasksRepository
}