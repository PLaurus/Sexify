package com.lauruscorp.sexifyapp.features.main.di.modules.domain

import com.lauruscorp.features.maindomain.api.MainDomainApi
import com.lauruscorp.features.maindomain.api.MainDomainDependencies
import com.lauruscorp.features.maindomain.api.MainDomainFactory
import com.lauruscorp.features.maindomain.store.MainStore
import com.lauruscorp.sexifyapp.features.main.di.component.MainActivityComponent
import com.lauruscorp.sexifyapp.features.main.di.component.scope.MainActivityScope
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal interface MainDomainModule {
	@Binds
	fun provideMainDomainDependencies(
		mainActivityComponent: MainActivityComponent
	): MainDomainDependencies
	
	companion object {
		@Provides
		fun provideMainDomainFactory(
			dependencies: MainDomainDependencies
		): MainDomainFactory {
			return MainDomainFactory(dependencies)
		}
		
		@Provides
		@MainActivityScope
		fun provideMainDomainApi(
			mainDomainFactory: MainDomainFactory
		): MainDomainApi {
			return mainDomainFactory.create()
		}
		
		@Provides
		fun provideMainStore(
			mainDomainApi: MainDomainApi
		): MainStore {
			return mainDomainApi.getStore()
		}
	}
}