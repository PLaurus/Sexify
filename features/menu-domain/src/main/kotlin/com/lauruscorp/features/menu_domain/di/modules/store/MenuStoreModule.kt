package com.lauruscorp.features.menu_domain.di.modules.store

import com.arkivanov.mvikotlin.core.store.Bootstrapper
import com.arkivanov.mvikotlin.core.store.Executor
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.lauruscorp.core_jvm.di.dagger.qualifiers.mvi.InitialStateQualifier
import com.lauruscorp.core_jvm.di.dagger.qualifiers.mvi.StoreNameQualifier
import com.lauruscorp.features.menu_domain.store.MenuExecutor
import com.lauruscorp.features.menu_domain.store.MenuReducer
import com.lauruscorp.features.menu_domain.store.MenuStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Provider
import javax.inject.Singleton

@Module
internal abstract class MenuStoreModule {

    companion object {
        @Provides
        @Singleton
        fun provideStore(
            storeFactory: StoreFactory,
            @StoreNameQualifier storeName: String,
            @InitialStateQualifier initialState: MenuStore.State,
            bootstrapper: @JvmSuppressWildcards Bootstrapper<MenuStore.Action>,
            executorProvider: @JvmSuppressWildcards Provider<Executor<MenuStore.Intent, MenuStore.Action, MenuStore.State, MenuStore.Message, MenuStore.Label>>,
            reducer: @JvmSuppressWildcards Reducer<MenuStore.State, MenuStore.Message>
        ): MenuStore {
            return object : MenuStore,
                            Store<MenuStore.Intent, MenuStore.State, MenuStore.Label> by storeFactory.create(
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
            return MenuStore::class.java.simpleName
        }

        @Provides
        @InitialStateQualifier
        fun provideInitialState(): MenuStore.State {
            return MenuStore.State(someValue = null)
        }

        @Provides
        fun provideBootstrapper(): Bootstrapper<MenuStore.Action> {
            return SimpleBootstrapper()
        }
    }

    @Binds
    abstract fun provideExecutor(
        executor: MenuExecutor
    ): Executor<MenuStore.Intent, MenuStore.Action, MenuStore.State, MenuStore.Message, MenuStore.Label>

    @Binds
    abstract fun provideReducer(
        reducer: MenuReducer
    ): Reducer<MenuStore.State, MenuStore.Message>
}