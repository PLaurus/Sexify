package com.lauruscorp.features.example.examplefeature.api

import com.lauruscorp.features.example.examplefeature.di.component.ExampleFeatureComponent

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