package com.lauruscorp.exampledomain.di

import com.lauruscorp.exampledomain.api.ExampleDomainApi
import com.lauruscorp.exampledomain.api.ExampleDomainDependencies
import com.lauruscorp.exampledomain.di.modules.store.ExampleStoreModule
import com.lauruscorp.exampledomain.di.modules.store.qualifiers.InitialAQualifier
import com.lauruscorp.exampledomain.di.modules.store.qualifiers.InitialBQualifier
import com.lauruscorp.exampledomain.di.modules.store.qualifiers.InitialOperationQualifier
import com.lauruscorp.exampledomain.entities.Operation
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
	dependencies = [
		ExampleDomainDependencies::class
	],
	modules = [
		ExampleStoreModule::class
	]
)
internal interface ExampleComponent : ExampleDomainApi {
	@Component.Factory
	interface Factory {
		fun create(
			dependencies: ExampleDomainDependencies,
			@BindsInstance @InitialOperationQualifier initialOperation: Operation,
			@BindsInstance @InitialAQualifier initialA: Float,
			@BindsInstance @InitialBQualifier initialB: Float
		): ExampleComponent
	}
}