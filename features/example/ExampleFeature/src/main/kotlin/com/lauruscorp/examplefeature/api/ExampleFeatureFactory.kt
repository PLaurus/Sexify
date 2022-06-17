package com.lauruscorp.examplefeature.api

import com.lauruscorp.examplefeature.di.ExampleFeatureComponent
import com.lauruscorp.examplefeature.di.ExampleFeatureComponentsRegistry

class ExampleFeatureFactory(
	private val dependencies: ExampleFeatureDependencies
) {
	// pass here initial state of the domain
	fun create(/* here*/): ExampleFeatureApi {
		return ExampleFeatureComponentsRegistry.createAndRegister {
			ExampleFeatureComponent
				.create(
					featureId = System.currentTimeMillis(),
					dependencies = dependencies
				)
		}
	}
}