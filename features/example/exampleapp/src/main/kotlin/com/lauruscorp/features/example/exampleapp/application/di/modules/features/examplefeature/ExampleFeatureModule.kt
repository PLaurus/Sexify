package com.lauruscorp.features.example.exampleapp.application.di.modules.features.examplefeature

import com.lauruscorp.examplefeature.api.ExampleFeatureDependencies
import com.lauruscorp.examplefeature.api.ExampleFeatureFactory
import com.lauruscorp.features.example.exampleapp.application.di.component.ExampleApplicationComponent
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class ExampleFeatureModule {
	companion object {
		@Provides
		fun provideExampleFeatureFactory(
			dependencies: ExampleFeatureDependencies
		): ExampleFeatureFactory {
			return ExampleFeatureFactory(dependencies)
		}
	}
	
	@Binds
	abstract fun provideExampleFeatureDependencies(
		component: ExampleApplicationComponent
	): ExampleFeatureDependencies
}