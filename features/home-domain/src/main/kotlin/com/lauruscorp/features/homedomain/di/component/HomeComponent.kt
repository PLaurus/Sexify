package com.lauruscorp.features.homedomain.di.component

import com.lauruscorp.features.homedomain.api.HomeDomainApi
import com.lauruscorp.features.homedomain.api.HomeDomainDependencies
import com.lauruscorp.features.homedomain.di.modules.store.HomeStoreModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
	dependencies = [
		HomeDomainDependencies::class
	],
	modules = [
		HomeStoreModule::class
	]
)
internal interface HomeComponent : HomeDomainApi {
	@Component.Factory
	interface Factory {
		fun create(
			dependencies: HomeDomainDependencies
		): HomeComponent
	}
	
	companion object {
		operator fun invoke(
			dependencies: HomeDomainDependencies
		): HomeComponent {
			return DaggerHomeComponent.factory()
				.create(dependencies)
		}
	}
}