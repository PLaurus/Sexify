package com.lauruscorp.sexify_domain.loading.di.modules.store

import com.arkivanov.mvikotlin.core.store.*
import com.lauruscorp.core.mvi.InitialStateQualifier
import com.lauruscorp.core.mvi.StoreNameQualifier
import com.lauruscorp.sexify_domain.loading.entities.LoadingState
import com.lauruscorp.sexify_domain.loading.store.LoadingExecutor
import com.lauruscorp.sexify_domain.loading.store.LoadingReducer
import com.lauruscorp.sexify_domain.loading.store.LoadingStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Provider
import javax.inject.Singleton

@Module
internal abstract class LoadingStoreModule {
    companion object {
        @Provides
        @Singleton
        fun provideStore(
            storeFactory: StoreFactory,
            @StoreNameQualifier storeName: String,
            @InitialStateQualifier initialState: LoadingStore.State,
            bootstrapper: @JvmSuppressWildcards Bootstrapper<LoadingStore.Action>,
            executorProvider: @JvmSuppressWildcards Provider<Executor<Any, LoadingStore.Action, LoadingStore.State, LoadingStore.Message, Any>>,
            reducer: @JvmSuppressWildcards Reducer<LoadingStore.State, LoadingStore.Message>
        ): LoadingStore {
            return object : LoadingStore,
                Store<Any, LoadingStore.State, Any> by storeFactory.create(
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
            return LoadingStore::class.java.simpleName
        }

        @Provides
        @InitialStateQualifier
        fun provideInitialState(): LoadingStore.State {
            return LoadingStore.State(
                loadingState = LoadingState.Loading
            )
        }

        @Provides
        fun provideBootstrapper(): Bootstrapper<LoadingStore.Action> {
            return SimpleBootstrapper(LoadingStore.Action.StartLoading)
        }
    }

    @Binds
    abstract fun provideExecutor(
        executor: LoadingExecutor
    ): Executor<Any, LoadingStore.Action, LoadingStore.State, LoadingStore.Message, Any>

    @Binds
    abstract fun provideReducer(
        reducer: LoadingReducer
    ): Reducer<LoadingStore.State, LoadingStore.Message>
}