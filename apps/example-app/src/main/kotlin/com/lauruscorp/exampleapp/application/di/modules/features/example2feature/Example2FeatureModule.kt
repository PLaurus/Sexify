package com.lauruscorp.exampleapp.application.di.modules.features.example2feature

import com.lauruscorp.exampleapp.application.di.component.ExampleApplicationComponent
import com.lauruscorp.features.example.example2feature.api.Example2FeatureDependencies
import com.lauruscorp.features.example.example2feature.api.Example2FeatureLauncher
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class Example2FeatureModule {
	@Binds
	abstract fun provideExample2FeatureDependencies(
		component: ExampleApplicationComponent
	): Example2FeatureDependencies
	
	companion object {
		@Provides
		fun provideExample2FeatureLauncher(
			dependencies: Example2FeatureDependencies
		): Example2FeatureLauncher {
			return Example2FeatureLauncher(dependencies)
		}
	}
}