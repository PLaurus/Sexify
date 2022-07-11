package com.lauruscorp.features.gamedomain.di.modules.store

import com.arkivanov.mvikotlin.core.store.Bootstrapper
import com.arkivanov.mvikotlin.core.store.Executor
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.lauruscorp.core.di.dagger.qualifiers.mvi.InitialStateQualifier
import com.lauruscorp.core.di.dagger.qualifiers.mvi.StoreNameQualifier
import com.lauruscorp.features.gamedomain.store.GameExecutor
import com.lauruscorp.features.gamedomain.store.GameReducer
import com.lauruscorp.features.gamedomain.store.GameStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Provider
import javax.inject.Singleton

@Module
internal abstract class GameStoreModule {
	
	companion object {
		@Provides
		@Singleton
		fun provideStore(
			storeFactory: StoreFactory,
			@StoreNameQualifier storeName: String,
			@InitialStateQualifier initialState: GameStore.State,
			bootstrapper: @JvmSuppressWildcards Bootstrapper<GameStore.Action>,
			executorProvider: @JvmSuppressWildcards Provider<Executor<GameStore.Intent, GameStore.Action, GameStore.State, GameStore.Message, GameStore.Label>>,
			reducer: @JvmSuppressWildcards Reducer<GameStore.State, GameStore.Message>
		): GameStore {
			return object : GameStore,
			                Store<GameStore.Intent, GameStore.State, GameStore.Label> by storeFactory.create(
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
			return GameStore::class.java.simpleName
		}
		
		@Provides
		@InitialStateQualifier
		fun provideInitialState(): GameStore.State {
			return GameStore.State(someValue = null)
		}
		
		@Provides
		fun provideBootstrapper(): Bootstrapper<GameStore.Action> {
			return SimpleBootstrapper()
		}
	}
	
	@Binds
	abstract fun provideExecutor(
		executor: GameExecutor
	): Executor<GameStore.Intent, GameStore.Action, GameStore.State, GameStore.Message, GameStore.Label>
	
	@Binds
	abstract fun provideReducer(
		reducer: GameReducer
	): Reducer<GameStore.State, GameStore.Message>
}