package com.lauruscorp.features.maindomain.di.modules.store

import com.arkivanov.mvikotlin.core.store.Bootstrapper
import com.arkivanov.mvikotlin.core.store.Executor
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.lauruscorp.core.di.dagger.qualifiers.mvi.InitialStateQualifier
import com.lauruscorp.core.di.dagger.qualifiers.mvi.StoreNameQualifier
import com.lauruscorp.features.maindomain.store.MainExecutor
import com.lauruscorp.features.maindomain.store.MainReducer
import com.lauruscorp.features.maindomain.store.MainStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Provider
import javax.inject.Singleton

@Module
internal abstract class MainStoreModule {
	
	companion object {
		@Provides
		@Singleton
		fun provideStore(
			storeFactory: StoreFactory,
			@StoreNameQualifier storeName: String,
			@InitialStateQualifier initialState: MainStore.State,
			bootstrapper: @JvmSuppressWildcards Bootstrapper<MainStore.Action>,
			executorProvider: @JvmSuppressWildcards Provider<Executor<MainStore.Intent, MainStore.Action, MainStore.State, MainStore.Message, MainStore.Label>>,
			reducer: @JvmSuppressWildcards Reducer<MainStore.State, MainStore.Message>
		): MainStore {
			return object : MainStore,
			                Store<MainStore.Intent, MainStore.State, MainStore.Label> by storeFactory.create(
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
			return MainStore::class.java.simpleName
		}
		
		@Provides
		@InitialStateQualifier
		fun provideInitialState(): MainStore.State {
			return MainStore.State(someValue = null)
		}
		
		@Provides
		fun provideBootstrapper(): Bootstrapper<MainStore.Action> {
			return SimpleBootstrapper()
		}
	}
	
	@Binds
	abstract fun provideExecutor(
		executor: MainExecutor
	): Executor<MainStore.Intent, MainStore.Action, MainStore.State, MainStore.Message, MainStore.Label>
	
	@Binds
	abstract fun provideReducer(
		reducer: MainReducer
	): Reducer<MainStore.State, MainStore.Message>
}