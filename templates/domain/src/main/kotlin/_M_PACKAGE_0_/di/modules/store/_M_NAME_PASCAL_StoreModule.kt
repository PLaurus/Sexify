package _M_PACKAGE_0_.di.modules.store

import _M_PACKAGE_0_.store._M_NAME_PASCAL_Executor
import _M_PACKAGE_0_.store._M_NAME_PASCAL_Reducer
import _M_PACKAGE_0_.store._M_NAME_PASCAL_Store
import com.arkivanov.mvikotlin.core.store.*
import com.lauruscorp.core.mvi.InitialStateQualifier
import com.lauruscorp.core.mvi.StoreNameQualifier
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Provider
import javax.inject.Singleton

@Module
internal abstract class _M_NAME_PASCAL_StoreModule {

    companion object {
        @Provides
        @Singleton
        fun provideStore(
            storeFactory: StoreFactory,
            @StoreNameQualifier storeName: String,
            @InitialStateQualifier initialState: _M_NAME_PASCAL_Store.State,
            bootstrapper: @JvmSuppressWildcards Bootstrapper<_M_NAME_PASCAL_Store.Action>,
            executorProvider: @JvmSuppressWildcards Provider<Executor<_M_NAME_PASCAL_Store.Intent, _M_NAME_PASCAL_Store.Action, _M_NAME_PASCAL_Store.State, _M_NAME_PASCAL_Store.Message, _M_NAME_PASCAL_Store.Label>>,
            reducer: @JvmSuppressWildcards Reducer<_M_NAME_PASCAL_Store.State, _M_NAME_PASCAL_Store.Message>
        ): _M_NAME_PASCAL_Store {
            return object : _M_NAME_PASCAL_Store,
                Store<_M_NAME_PASCAL_Store.Intent, _M_NAME_PASCAL_Store.State, _M_NAME_PASCAL_Store.Label> by storeFactory.create(
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
            return _M_NAME_PASCAL_Store::class.java.simpleName
        }

        @Provides
        @InitialStateQualifier
        fun provideInitialState(): _M_NAME_PASCAL_Store.State {
            return _M_NAME_PASCAL_Store.State(someValue = null)
        }

        @Provides
        fun provideBootstrapper(): Bootstrapper<_M_NAME_PASCAL_Store.Action> {
            return SimpleBootstrapper()
        }
    }

    @Binds
    abstract fun provideExecutor(
        executor: _M_NAME_PASCAL_Executor
    ): Executor<_M_NAME_PASCAL_Store.Intent, _M_NAME_PASCAL_Store.Action, _M_NAME_PASCAL_Store.State, _M_NAME_PASCAL_Store.Message, _M_NAME_PASCAL_Store.Label>

    @Binds
    abstract fun provideReducer(
        reducer: _M_NAME_PASCAL_Reducer
    ): Reducer<_M_NAME_PASCAL_Store.State, _M_NAME_PASCAL_Store.Message>
}