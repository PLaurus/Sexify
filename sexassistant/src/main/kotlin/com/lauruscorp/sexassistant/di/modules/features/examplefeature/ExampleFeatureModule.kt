package com.lauruscorp.sexassistant.di.modules.features.examplefeature

import com.lauruscorp.examplefeature.api.ExampleFeatureDependencies
import com.lauruscorp.examplefeature.api.ExampleFeatureFactory
import com.lauruscorp.sexassistant.di.component.SexAssistantComponent
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
		component: SexAssistantComponent
	): ExampleFeatureDependencies
}