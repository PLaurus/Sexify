package com.lauruscorp.features.example.exampledomain.di.component

import com.lauruscorp.features.example.exampledomain.api.ExampleDomainApi
import com.lauruscorp.features.example.exampledomain.api.ExampleDomainDependencies
import com.lauruscorp.features.example.exampledomain.di.modules.store.ExampleStoreModule
import com.lauruscorp.features.example.exampledomain.di.modules.store.qualifiers.InitialAQualifier
import com.lauruscorp.features.example.exampledomain.di.modules.store.qualifiers.InitialBQualifier
import com.lauruscorp.features.example.exampledomain.di.modules.store.qualifiers.InitialOperationQualifier
import com.lauruscorp.features.example.exampledomain.entities.Operation
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
			@BindsInstance @InitialAQualifier initialA: Int,
			@BindsInstance @InitialBQualifier initialB: Int
		): ExampleComponent
	}
}