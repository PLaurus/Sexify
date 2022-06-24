package com.lauruscorp.features.example.examplefeature.di.modules.domains.exampledomain

import com.lauruscorp.features.example.exampledomain.api.ExampleDomainApi
import com.lauruscorp.features.example.exampledomain.api.ExampleDomainDependencies
import com.lauruscorp.features.example.exampledomain.api.ExampleDomainFactory
import com.lauruscorp.features.example.exampledomain.entities.Operation
import com.lauruscorp.features.example.exampledomain.store.ExampleStore
import com.lauruscorp.features.example.examplefeature.di.component.ExampleFeatureComponent
import com.lauruscorp.features.example.examplefeature.di.component.scope.ExampleFeatureScope
import com.lauruscorp.features.example.examplefeature.di.modules.domains.exampledomain.qualifiers.InitialExampleStoreAQualifier
import com.lauruscorp.features.example.examplefeature.di.modules.domains.exampledomain.qualifiers.InitialExampleStoreBQualifier
import com.lauruscorp.features.example.examplefeature.di.modules.domains.exampledomain.qualifiers.InitialExampleStoreOperationQualifier
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal interface ExampleDomainModule {
	@Binds
	fun provideExampleDomainDependencies(
		exampleFeatureComponent: ExampleFeatureComponent
	): ExampleDomainDependencies
	
	companion object {
		@Provides
		fun provideExampleDomainFactory(
			dependencies: ExampleDomainDependencies
		): ExampleDomainFactory {
			return ExampleDomainFactory(dependencies)
		}
		
		@Provides
		@ExampleFeatureScope
		fun provideExampleDomainApi(
			exampleDomainFactory: ExampleDomainFactory,
			@InitialExampleStoreOperationQualifier initialOperation: Operation,
			@InitialExampleStoreAQualifier initialA: Int,
			@InitialExampleStoreBQualifier initialB: Int
		): ExampleDomainApi {
			return exampleDomainFactory.create(
				initialOperation = initialOperation,
				initialA = initialA,
				initialB = initialB
			)
		}
		
		@Provides
		fun provideExampleStore(
			exampleDomainApi: ExampleDomainApi
		): ExampleStore {
			return exampleDomainApi.getStore()
		}
		
		@Provides
		@InitialExampleStoreOperationQualifier
		fun provideInitialExampleStoreOperation(): Operation = Operation.Multiplication
		
		@Provides
		@InitialExampleStoreAQualifier
		fun provideInitialExampleStoreA(): Int = 3
		
		@Provides
		@InitialExampleStoreBQualifier
		fun provideInitialExampleStoreB(): Int = 9
	}
}