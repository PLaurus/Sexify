package com.lauruscorp.features.example.exampledomain.api

import com.lauruscorp.features.example.exampledomain.di.component.ExampleComponent
import com.lauruscorp.features.example.exampledomain.entities.Operation

class ExampleDomainFactory(
	private val dependencies: ExampleDomainDependencies
) {
	fun create(
		initialOperation: Operation,
		initialA: Int,
		initialB: Int
	): ExampleDomainApi {
		return ExampleComponent(
			dependencies = dependencies,
			initialOperation = initialOperation,
			initialA = initialA,
			initialB = initialB
		)
	}
}