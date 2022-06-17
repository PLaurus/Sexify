package com.lauruscorp.examplefeature.di.modules.domains.exampledomain

import com.lauruscorp.exampledomain.ExampleStore
import com.lauruscorp.exampledomain.api.ExampleDomainApi
import com.lauruscorp.exampledomain.api.ExampleDomainDependencies
import com.lauruscorp.exampledomain.api.ExampleDomainFactory
import com.lauruscorp.exampledomain.entities.Operation
import com.lauruscorp.examplefeature.di.ExampleFeatureComponent
import com.lauruscorp.examplefeature.di.modules.domains.exampledomain.qualifiers.InitialExampleStoreAQualifier
import com.lauruscorp.examplefeature.di.modules.domains.exampledomain.qualifiers.InitialExampleStoreBQualifier
import com.lauruscorp.examplefeature.di.modules.domains.exampledomain.qualifiers.InitialExampleStoreOperationQualifier
import com.lauruscorp.examplefeature.di.scope.ExampleFeatureScope
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
			@InitialExampleStoreAQualifier initialA: Float,
			@InitialExampleStoreBQualifier initialB: Float
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
		fun provideInitialExampleStoreA(): Float = 3.0f
		
		@Provides
		@InitialExampleStoreBQualifier
		fun provideInitialExampleStoreB(): Float = 9.0f
	}
}