package com.lauruscorp.features.maindomain.di.component

import com.lauruscorp.features.maindomain.api.MainDomainApi
import com.lauruscorp.features.maindomain.dependencies.MainDomainDependencies
import com.lauruscorp.features.maindomain.di.modules.store.MainStoreModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
	dependencies = [
		MainDomainDependencies::class
	],
	modules = [
		MainStoreModule::class
	]
)
internal interface MainComponent : MainDomainApi {
	@Component.Factory
	interface Factory {
		fun create(
			dependencies: MainDomainDependencies
		): MainComponent
	}
	
	companion object {
		operator fun invoke(
			dependencies: MainDomainDependencies
		): MainComponent {
			return DaggerMainComponent.factory()
				.create(dependencies)
		}
	}
}