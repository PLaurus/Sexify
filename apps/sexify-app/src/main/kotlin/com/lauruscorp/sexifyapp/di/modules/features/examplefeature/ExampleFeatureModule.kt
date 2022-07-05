package com.lauruscorp.sexifyapp.di.modules.features.examplefeature

import com.lauruscorp.features.example.examplefeature.api.ExampleFeatureDependencies
import com.lauruscorp.features.example.examplefeature.api.ExampleFeatureFactory
import com.lauruscorp.sexifyapp.di.component.SexifyComponent
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
		component: SexifyComponent
	): ExampleFeatureDependencies
}