package com.lauruscorp.exampledomain.api

import com.lauruscorp.exampledomain.di.DaggerExampleComponent
import com.lauruscorp.exampledomain.entities.Operation

class ExampleDomainFactory(
	private val dependencies: ExampleDomainDependencies
) {
	fun create(
		initialOperation: Operation,
		initialA: Float,
		initialB: Float
	): ExampleDomainApi {
		return DaggerExampleComponent.factory()
			.create(
				dependencies = dependencies,
				initialOperation = initialOperation,
				initialA = initialA,
				initialB = initialB
			)
	}
}