package application.features.home.di

import application.features.home.dependencies.HomeFeatureDependenciesImpl
import dagger.Binds
import dagger.Module
import features.home.di.component.dependencies.HomeFeatureDependencies

@Module
internal interface HomeFeatureModule {
	@Binds
	fun provideHomeFeatureDependencies(
		dependencies: HomeFeatureDependenciesImpl
	): HomeFeatureDependencies
}