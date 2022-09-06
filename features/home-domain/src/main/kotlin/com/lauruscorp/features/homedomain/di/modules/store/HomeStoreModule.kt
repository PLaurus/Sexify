package com.lauruscorp.features.homedomain.di.modules.store

import com.arkivanov.mvikotlin.core.store.*
import com.lauruscorp.core_jvm.di.dagger.qualifiers.mvi.InitialStateQualifier
import com.lauruscorp.core_jvm.di.dagger.qualifiers.mvi.StoreNameQualifier
import com.lauruscorp.features.homedomain.store.HomeExecutor
import com.lauruscorp.features.homedomain.store.HomeReducer
import com.lauruscorp.features.homedomain.store.HomeStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Provider
import javax.inject.Singleton

@Module
internal abstract class HomeStoreModule {

    companion object {
        @Provides
        @Singleton
        fun provideStore(
            storeFactory: StoreFactory,
            @StoreNameQualifier storeName: String,
            @InitialStateQualifier initialState: HomeStore.State,
            bootstrapper: @JvmSuppressWildcards Bootstrapper<HomeStore.Action>,
            executorProvider: @JvmSuppressWildcards Provider<Executor<HomeStore.Intent, HomeStore.Action, HomeStore.State, HomeStore.Message, HomeStore.Label>>,
            reducer: @JvmSuppressWildcards Reducer<HomeStore.State, HomeStore.Message>
        ): HomeStore {
            return object : HomeStore,
                Store<HomeStore.Intent, HomeStore.State, HomeStore.Label> by storeFactory.create(
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
            return HomeStore::class.java.simpleName
        }

        @Provides
        @InitialStateQualifier
        fun provideInitialState(): HomeStore.State {
            return HomeStore.State(someValue = null)
        }

        @Provides
        fun provideBootstrapper(): Bootstrapper<HomeStore.Action> {
            return SimpleBootstrapper()
        }
    }

    @Binds
    abstract fun provideExecutor(
        executor: HomeExecutor
    ): Executor<HomeStore.Intent, HomeStore.Action, HomeStore.State, HomeStore.Message, HomeStore.Label>

    @Binds
    abstract fun provideReducer(
        reducer: HomeReducer
    ): Reducer<HomeStore.State, HomeStore.Message>
}