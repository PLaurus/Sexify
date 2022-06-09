package com.lauruscorp.examplefeature.di

import com.lauruscorp.examplefeature.ExampleFeature
import com.lauruscorp.examplefeature.di.dependencies.ExampleFeatureDependencies
import com.lauruscorp.examplefeature.di.modules.FeatureModule
import com.lauruscorp.examplefeature.di.qualifiers.FeatureIdQualifier
import com.lauruscorp.examplefeature.presentation.ExampleFeatureActivity
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
internal interface ExampleFeatureComponent : ExampleFeature {
	@FeatureIdQualifier
	fun getFeatureId(): Long
	
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