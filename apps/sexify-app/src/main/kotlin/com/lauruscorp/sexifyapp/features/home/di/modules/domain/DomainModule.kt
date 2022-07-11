package com.lauruscorp.sexifyapp.features.home.di.modules.domain

import com.lauruscorp.features.homedomain.api.HomeDomainApi
import com.lauruscorp.features.homedomain.api.HomeDomainDependencies
import com.lauruscorp.features.homedomain.api.HomeDomainFactory
import com.lauruscorp.features.homedomain.store.HomeStore
import com.lauruscorp.sexifyapp.features.home.di.component.HomeFeatureComponent
import com.lauruscorp.sexifyapp.features.main.di.component.scope.MainActivityScope
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal interface DomainModule {
	@Binds
	fun provideHomeDomainDependencies(
		homeFeatureComponent: HomeFeatureComponent
	): HomeDomainDependencies
	
	companion object {
		@Provides
		fun provideHomeDomainFactory(
			dependencies: HomeDomainDependencies
		): HomeDomainFactory {
			return HomeDomainFactory(dependencies)
		}
		
		@Provides
		@MainActivityScope
		fun provideHomeDomainApi(
			homeDomainFactory: HomeDomainFactory
		): HomeDomainApi {
			return homeDomainFactory.create()
		}
		
		@Provides
		fun provideHomeStore(
			homeDomainApi: HomeDomainApi
		): HomeStore {
			return homeDomainApi.getStore()
		}
	}
}