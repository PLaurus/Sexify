package com.lauruscorp.features.gamedomain.di.component

import com.lauruscorp.features.gamedomain.api.GameDomainApi
import com.lauruscorp.features.gamedomain.api.GameDomainDependencies
import com.lauruscorp.features.gamedomain.di.modules.store.GameStoreModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
	dependencies = [
		GameDomainDependencies::class
	],
	modules = [
		GameStoreModule::class
	]
)
internal interface GameComponent : GameDomainApi {
	@Component.Factory
	interface Factory {
		fun create(
			dependencies: GameDomainDependencies
		): GameComponent
	}
	
	companion object {
		operator fun invoke(
			dependencies: GameDomainDependencies
		): GameComponent {
			return DaggerGameComponent.factory()
				.create(dependencies)
		}
	}
}