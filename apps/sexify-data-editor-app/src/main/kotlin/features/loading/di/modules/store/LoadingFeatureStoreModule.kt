package features.loading.di.modules.store

import com.arkivanov.mvikotlin.core.store.*
import com.lauruscorp.core_jvm.di.dagger.qualifiers.mvi.InitialStateQualifier
import com.lauruscorp.core_jvm.di.dagger.qualifiers.mvi.StoreNameQualifier
import dagger.Binds
import dagger.Module
import dagger.Provides
import features.loading.di.component.scope.LoadingFeatureScope
import features.loading.domain.store.LoadingFeatureExecutor
import features.loading.domain.store.LoadingFeatureReducer
import features.loading.domain.store.LoadingFeatureStore
import features.loading.entities.LoadingState
import javax.inject.Provider

@Module
internal abstract class LoadingFeatureStoreModule {
	companion object {
		@Provides
		@LoadingFeatureScope
		fun provideStore(
			storeFactory: StoreFactory,
			@StoreNameQualifier storeName: String,
			@InitialStateQualifier initialState: LoadingFeatureStore.State,
			bootstrapper: @JvmSuppressWildcards Bootstrapper<LoadingFeatureStore.Action>,
			executorProvider: @JvmSuppressWildcards Provider<Executor<Any, LoadingFeatureStore.Action, LoadingFeatureStore.State, LoadingFeatureStore.Message, Any>>,
			reducer: @JvmSuppressWildcards Reducer<LoadingFeatureStore.State, LoadingFeatureStore.Message>
		): LoadingFeatureStore {
			return object : LoadingFeatureStore,
			                Store<Any, LoadingFeatureStore.State, Any> by storeFactory.create(
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
			return LoadingFeatureStore::class.java.simpleName
		}
		
		@Provides
		@InitialStateQualifier
		fun provideInitialState(): LoadingFeatureStore.State {
			return LoadingFeatureStore.State(
				loadingState = LoadingState.Loading
			)
		}
		
		@Provides
		fun provideBootstrapper(): Bootstrapper<LoadingFeatureStore.Action> {
			return SimpleBootstrapper(LoadingFeatureStore.Action.StartLoading)
		}
	}
	
	@Binds
	abstract fun provideLoadingFeatureExecutor(
		executor: LoadingFeatureExecutor
	): Executor<Any, LoadingFeatureStore.Action, LoadingFeatureStore.State, LoadingFeatureStore.Message, Any>
	
	@Binds
	abstract fun provideLoadingFeatureReducer(
		reducer: LoadingFeatureReducer
	): Reducer<LoadingFeatureStore.State, LoadingFeatureStore.Message>
}