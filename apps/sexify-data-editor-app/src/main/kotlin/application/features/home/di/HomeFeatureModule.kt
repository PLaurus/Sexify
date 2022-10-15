package application.features.home.di

import application.features.home.data.repository.HomeFeatureTasksRepository
import application.features.home.dependencies.HomeFeatureDependenciesImpl
import dagger.Binds
import dagger.Module
import features.home.dependencies.HomeFeatureDependencies
import features.home.domain.repository.TasksRepository

@Module
internal interface HomeFeatureModule {
	@Binds
	fun provideHomeFeatureDependencies(
		dependencies: HomeFeatureDependenciesImpl
	): HomeFeatureDependencies

	@Binds
	fun provideTasksRepository(
		repository: HomeFeatureTasksRepository
	): TasksRepository
}