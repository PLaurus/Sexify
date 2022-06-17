package com.lauruscorp.exampledomain.di.modules.store

import com.arkivanov.mvikotlin.core.store.Bootstrapper
import com.arkivanov.mvikotlin.core.store.Executor
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.lauruscorp.core.di.dagger.qualifiers.mvi.InitialStateQualifier
import com.lauruscorp.core.di.dagger.qualifiers.mvi.StoreNameQualifier
import com.lauruscorp.exampledomain.ExampleExecutor
import com.lauruscorp.exampledomain.ExampleReducer
import com.lauruscorp.exampledomain.ExampleStore
import com.lauruscorp.exampledomain.di.modules.store.qualifiers.InitialAQualifier
import com.lauruscorp.exampledomain.di.modules.store.qualifiers.InitialBQualifier
import com.lauruscorp.exampledomain.di.modules.store.qualifiers.InitialOperationQualifier
import com.lauruscorp.exampledomain.entities.Operation
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Provider
import javax.inject.Singleton

@Module
internal abstract class ExampleStoreModule {
	
	companion object {
		@Provides
		@Singleton
		fun provideStore(
			storeFactory: StoreFactory,
			@StoreNameQualifier storeName: String,
			@InitialStateQualifier initialState: ExampleStore.State,
			bootstrapper: @JvmSuppressWildcards Bootstrapper<ExampleStore.Action>,
			executorProvider: @JvmSuppressWildcards Provider<Executor<ExampleStore.Intent, ExampleStore.Action, ExampleStore.State, ExampleStore.Message, ExampleStore.Label>>,
			reducer: @JvmSuppressWildcards Reducer<ExampleStore.State, ExampleStore.Message>
		): ExampleStore {
			return object : ExampleStore,
			                Store<ExampleStore.Intent, ExampleStore.State, ExampleStore.Label> by storeFactory.create(
				                name = storeName,
				                initialState = initialState,
				                bootstrapper = bootstrapper,
				                executorFactory = executorProvider::get,
				                reducer = reducer
			                ) {}
		}
		
		@Provides
		@StoreNameQualifier
		fun provideStoreName(): String {
			return ExampleStore::class.java.simpleName
		}
		
		@Provides
		@InitialStateQualifier
		fun provideInitialState(): ExampleStore.State {
			return ExampleStore.State()
		}
		
		@Provides
		fun provideBootstrapper(
			@InitialOperationQualifier initialOperation: Operation,
			@InitialAQualifier initialA: Float,
			@InitialBQualifier initialB: Float
		): Bootstrapper<ExampleStore.Action> {
			return SimpleBootstrapper(
				ExampleStore.Action.Initialize(
					operation = initialOperation,
					a = initialA,
					b = initialB
				)
			)
		}
	}
	
	@Binds
	abstract fun provideExampleExecutor(
		exampleExecutor: ExampleExecutor
	): Executor<ExampleStore.Intent, ExampleStore.Action, ExampleStore.State, ExampleStore.Message, ExampleStore.Label>
	
	@Binds
	abstract fun provideExampleReducer(
		exampleReducer: ExampleReducer
	): Reducer<ExampleStore.State, ExampleStore.Message>
}