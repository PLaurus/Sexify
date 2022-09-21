package application.features.loading.di

import application.features.loading.dependencies.LoadingFeatureDependenciesImpl
import application.features.loading.initializers.SexifyDatabaseInitializer
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet
import features.loading.di.component.dependencies.LoadingFeatureDependencies
import features.loading.domain.initializers.Initializer

@Module
internal interface LoadingFeatureModule {
	@Binds
	fun provideLoadingFeatureDependencies(
		dependencies: LoadingFeatureDependenciesImpl
	): LoadingFeatureDependencies
	
	@Binds
	@IntoSet
	fun provideSexifyDatabaseInitializer(
		initializer: SexifyDatabaseInitializer
	): Initializer
}