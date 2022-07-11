package com.lauruscorp.features.gamedomain.api

import com.lauruscorp.features.gamedomain.di.component.GameComponent

class GameDomainFactory(
	private val dependencies: GameDomainDependencies
) {
	fun create(): GameDomainApi {
		return GameComponent(
			dependencies = dependencies
		)
	}
}