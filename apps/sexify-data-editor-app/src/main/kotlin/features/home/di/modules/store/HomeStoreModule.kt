package features.home.di.modules.store

import com.arkivanov.mvikotlin.core.store.*
import com.lauruscorp.core_jvm.di.dagger.qualifiers.mvi.InitialStateQualifier
import com.lauruscorp.core_jvm.di.dagger.qualifiers.mvi.StoreNameQualifier
import dagger.Binds
import dagger.Module
import dagger.Provides
import features.home.di.component.scope.HomeFeatureScope
import features.home.domain.store.HomeExecutor
import features.home.domain.store.HomeReducer
import features.home.domain.store.HomeStore
import javax.inject.Provider

@Module
internal abstract class HomeStoreModule {
    companion object {
        @Provides
        @HomeFeatureScope
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
            return HomeStore.State(
                searchText = "",
                tasks = emptyList(),
                filteredTasks = emptyList()
            )
        }

        @Provides
        fun provideBootstrapper(): Bootstrapper<HomeStore.Action> {
            return SimpleBootstrapper(HomeStore.Action.LoadData)
        }
    }

    @Binds
    abstract fun provideHomeExecutor(
        homeExecutor: HomeExecutor
    ): Executor<HomeStore.Intent, HomeStore.Action, HomeStore.State, HomeStore.Message, HomeStore.Label>

    @Binds
    abstract fun provideHomeReducer(
        homeReducer: HomeReducer
    ): Reducer<HomeStore.State, HomeStore.Message>
}