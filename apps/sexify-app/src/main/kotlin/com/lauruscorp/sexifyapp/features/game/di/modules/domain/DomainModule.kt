package com.lauruscorp.sexifyapp.features.game.di.modules.domain

import com.lauruscorp.features.gamedomain.api.GameDomainApi
import com.lauruscorp.features.gamedomain.api.GameDomainDependencies
import com.lauruscorp.features.gamedomain.api.GameDomainFactory
import com.lauruscorp.features.gamedomain.store.GameStore
import com.lauruscorp.sexifyapp.features.game.di.component.GameFeatureComponent
import com.lauruscorp.sexifyapp.features.main.di.component.scope.MainActivityScope
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal interface DomainModule {
	@Binds
	fun provideGameDomainDependencies(
		gameFeatureComponent: GameFeatureComponent
	): GameDomainDependencies
	
	companion object {
		@Provides
		fun provideGameDomainFactory(
			dependencies: GameDomainDependencies
		): GameDomainFactory {
			return GameDomainFactory(dependencies)
		}
		
		@Provides
		@MainActivityScope
		fun provideGameDomainApi(
			gameDomainFactory: GameDomainFactory
		): GameDomainApi {
			return gameDomainFactory.create()
		}
		
		@Provides
		fun provideGameStore(
			gameDomainApi: GameDomainApi
		): GameStore {
			return gameDomainApi.getStore()
		}
	}
}