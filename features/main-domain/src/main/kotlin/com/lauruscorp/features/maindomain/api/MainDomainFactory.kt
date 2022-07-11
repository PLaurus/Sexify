package com.lauruscorp.features.maindomain.api

import com.lauruscorp.features.maindomain.di.component.MainComponent

class MainDomainFactory(
	private val dependencies: MainDomainDependencies
) {
	fun create(): MainDomainApi {
		return MainComponent(
			dependencies = dependencies
		)
	}
}