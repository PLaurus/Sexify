package com.lauruscorp.features.homedomain.api

import com.lauruscorp.features.homedomain.di.component.HomeComponent

class HomeDomainFactory(
	private val dependencies: HomeDomainDependencies
) {
	fun create(): HomeDomainApi {
		return HomeComponent(
			dependencies = dependencies
		)
	}
}