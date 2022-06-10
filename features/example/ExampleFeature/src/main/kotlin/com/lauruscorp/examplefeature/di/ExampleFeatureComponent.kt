package com.lauruscorp.examplefeature.di

import com.lauruscorp.examplefeature.ExampleFeatureApi
import com.lauruscorp.examplefeature.di.dependencies.ExampleFeatureDependencies
import com.lauruscorp.examplefeature.di.modules.FeatureModule
import com.lauruscorp.examplefeature.presentation.ExampleFeatureActivity
import com.lauruscorp.features.core.di.FeatureComponent
import com.lauruscorp.features.core.di.qualifiers.FeatureIdQualifier
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
	dependencies = [
		ExampleFeatureDependencies::class
	],
	modules = [
		FeatureModule::class
	]
)
internal interface ExampleFeatureComponent : FeatureComponent, ExampleFeatureApi {
	
	fun inject(exampleFeatureActivity: ExampleFeatureActivity)
	
	@Component.Factory
	interface Factory {
		fun create(
			@BindsInstance @FeatureIdQualifier featureId: Long,
			dependencies: ExampleFeatureDependencies
		): ExampleFeatureComponent
	}
	
	companion object {
		fun create(
			featureId: Long,
			dependencies: ExampleFeatureDependencies
		): ExampleFeatureComponent {
			return DaggerExampleFeatureComponent.factory()
				.create(featureId, dependencies)
		}
	}
}