package com.lauruscorp.features.couplenameseditordomain.di.modules.store

import com.arkivanov.mvikotlin.core.store.*
import com.lauruscorp.core_jvm.di.dagger.qualifiers.mvi.InitialStateQualifier
import com.lauruscorp.core_jvm.di.dagger.qualifiers.mvi.StoreNameQualifier
import com.lauruscorp.features.couplenameseditordomain.store.CoupleNamesEditorExecutor
import com.lauruscorp.features.couplenameseditordomain.store.CoupleNamesEditorReducer
import com.lauruscorp.features.couplenameseditordomain.store.CoupleNamesEditorStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Provider
import javax.inject.Singleton

@Module
internal abstract class CoupleNamesEditorStoreModule {

    companion object {
        @Provides
        @Singleton
        fun provideStore(
            storeFactory: StoreFactory,
            @StoreNameQualifier storeName: String,
            @InitialStateQualifier initialState: CoupleNamesEditorStore.State,
            bootstrapper: @JvmSuppressWildcards Bootstrapper<CoupleNamesEditorStore.Action>,
            executorProvider: @JvmSuppressWildcards Provider<Executor<CoupleNamesEditorStore.Intent, CoupleNamesEditorStore.Action, CoupleNamesEditorStore.State, CoupleNamesEditorStore.Message, CoupleNamesEditorStore.Label>>,
            reducer: @JvmSuppressWildcards Reducer<CoupleNamesEditorStore.State, CoupleNamesEditorStore.Message>
        ): CoupleNamesEditorStore {
            return object : CoupleNamesEditorStore,
                Store<CoupleNamesEditorStore.Intent, CoupleNamesEditorStore.State, CoupleNamesEditorStore.Label> by storeFactory.create(
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
            return CoupleNamesEditorStore::class.java.simpleName
        }

        @Provides
        @InitialStateQualifier
        fun provideInitialState(): CoupleNamesEditorStore.State {
            return TODO()//CoupleNamesEditorStore.State(someValue = null)
        }

        @Provides
        fun provideBootstrapper(): Bootstrapper<CoupleNamesEditorStore.Action> {
            return SimpleBootstrapper()
        }
    }

    @Binds
    abstract fun provideExecutor(
        executor: CoupleNamesEditorExecutor
    ): Executor<CoupleNamesEditorStore.Intent, CoupleNamesEditorStore.Action, CoupleNamesEditorStore.State, CoupleNamesEditorStore.Message, CoupleNamesEditorStore.Label>

    @Binds
    abstract fun provideReducer(
        reducer: CoupleNamesEditorReducer
    ): Reducer<CoupleNamesEditorStore.State, CoupleNamesEditorStore.Message>
}