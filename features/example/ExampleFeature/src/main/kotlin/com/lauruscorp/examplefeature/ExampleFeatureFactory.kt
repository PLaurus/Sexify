package com.lauruscorp.examplefeature

import com.lauruscorp.examplefeature.di.ExampleFeatureComponent
import com.lauruscorp.examplefeature.di.dependencies.ExampleFeatureDependencies

class ExampleFeatureFactory(
	private val dependencies: ExampleFeatureDependencies
) {
	// pass here initial state of the domain
	fun create(/* here*/): ExampleFeatureApi = ExampleFeatureComponentsRegistry.createAndRegister {
		ExampleFeatureComponent
			.create(
				featureId = System.currentTimeMillis(),
				dependencies = dependencies
			)
	}
}