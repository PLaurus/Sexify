package com.lauruscorp.examplefeature.api

import com.lauruscorp.examplefeature.di.component.ExampleFeatureComponent
import com.lauruscorp.examplefeature.di.component.ExampleFeatureComponentsRegistry

class ExampleFeatureFactory(
	private val dependencies: ExampleFeatureDependencies
) {
	// pass here initial state of the domain or callbacks
	fun create(/* here*/): ExampleFeatureApi {
		return ExampleFeatureComponent
			.create(
				featureId = System.nanoTime(),
				dependencies = dependencies
			)
	}
}