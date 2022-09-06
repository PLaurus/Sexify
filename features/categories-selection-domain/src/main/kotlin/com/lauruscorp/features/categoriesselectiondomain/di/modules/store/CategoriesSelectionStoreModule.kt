package com.lauruscorp.features.categoriesselectiondomain.di.modules.store

import com.arkivanov.mvikotlin.core.store.*
import com.lauruscorp.core_jvm.di.dagger.qualifiers.mvi.InitialStateQualifier
import com.lauruscorp.core_jvm.di.dagger.qualifiers.mvi.StoreNameQualifier
import com.lauruscorp.features.categoriesselectiondomain.store.CategoriesSelectionExecutor
import com.lauruscorp.features.categoriesselectiondomain.store.CategoriesSelectionReducer
import com.lauruscorp.features.categoriesselectiondomain.store.CategoriesSelectionStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Provider
import javax.inject.Singleton

@Module
internal abstract class CategoriesSelectionStoreModule {

    companion object {
        @Provides
        @Singleton
        fun provideStore(
            storeFactory: StoreFactory,
            @StoreNameQualifier storeName: String,
            @InitialStateQualifier initialState: CategoriesSelectionStore.State,
            bootstrapper: @JvmSuppressWildcards Bootstrapper<CategoriesSelectionStore.Action>,
            executorProvider: @JvmSuppressWildcards Provider<Executor<CategoriesSelectionStore.Intent, CategoriesSelectionStore.Action, CategoriesSelectionStore.State, CategoriesSelectionStore.Message, CategoriesSelectionStore.Label>>,
            reducer: @JvmSuppressWildcards Reducer<CategoriesSelectionStore.State, CategoriesSelectionStore.Message>
        ): CategoriesSelectionStore {
            return object : CategoriesSelectionStore,
                Store<CategoriesSelectionStore.Intent, CategoriesSelectionStore.State, CategoriesSelectionStore.Label> by storeFactory.create(
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
            return CategoriesSelectionStore::class.java.simpleName
        }

        @Provides
        @InitialStateQualifier
        fun provideInitialState(): CategoriesSelectionStore.State {
            return CategoriesSelectionStore.State(someValue = null)
        }

        @Provides
        fun provideBootstrapper(): Bootstrapper<CategoriesSelectionStore.Action> {
            return SimpleBootstrapper()
        }
    }

    @Binds
    abstract fun provideExecutor(
        executor: CategoriesSelectionExecutor
    ): Executor<CategoriesSelectionStore.Intent, CategoriesSelectionStore.Action, CategoriesSelectionStore.State, CategoriesSelectionStore.Message, CategoriesSelectionStore.Label>

    @Binds
    abstract fun provideReducer(
        reducer: CategoriesSelectionReducer
    ): Reducer<CategoriesSelectionStore.State, CategoriesSelectionStore.Message>
}