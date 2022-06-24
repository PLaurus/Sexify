package com.lauruscorp.features.example.example2feature.di.modules.domains.exampledomain

import com.lauruscorp.features.example.example2feature.di.component.Example2FeatureComponent
import com.lauruscorp.features.example.example2feature.di.component.scope.Example2FeatureScope
import com.lauruscorp.features.example.example2feature.di.modules.domains.exampledomain.qualifiers.InitialExampleStoreAQualifier
import com.lauruscorp.features.example.example2feature.di.modules.domains.exampledomain.qualifiers.InitialExampleStoreBQualifier
import com.lauruscorp.features.example.example2feature.di.modules.domains.exampledomain.qualifiers.InitialExampleStoreOperationQualifier
import com.lauruscorp.features.example.exampledomain.api.ExampleDomainApi
import com.lauruscorp.features.example.exampledomain.api.ExampleDomainDependencies
import com.lauruscorp.features.example.exampledomain.api.ExampleDomainFactory
import com.lauruscorp.features.example.exampledomain.entities.Operation
import com.lauruscorp.features.example.exampledomain.store.ExampleStore
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal interface ExampleDomainModule {
	@Binds
	fun provideExampleDomainDependencies(
		example2FeatureComponent: Example2FeatureComponent
	): ExampleDomainDependencies
	
	companion object {
		@Provides
		fun provideExampleDomainFactory(
			dependencies: ExampleDomainDependencies
		): ExampleDomainFactory {
			return ExampleDomainFactory(dependencies)
		}
		
		@Provides
		@Example2FeatureScope
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