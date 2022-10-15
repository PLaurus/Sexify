package com.lauruscorp.sexify_android.features.loading.di.modules.domain

import com.lauruscorp.sexify_android.features.loading.di.component.scope.LoadingFeatureScope
import com.lauruscorp.sexify_android.features.loading.domain.dependencies.LoadingDomainDependenciesImpl
import com.lauruscorp.sexify_domain.loading.LoadingDomain
import com.lauruscorp.sexify_domain.loading.LoadingDomainFactory
import com.lauruscorp.sexify_domain.loading.dependencies.LoadingDomainDependencies
import com.lauruscorp.sexify_domain.loading.store.LoadingStore
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal interface DomainModule {
    @Binds
    fun provideLoadingDomainDependencies(
        dependencies: LoadingDomainDependenciesImpl
    ): LoadingDomainDependencies

    companion object {
        @Provides
        fun provideLoadingDomainFactory(
            dependencies: LoadingDomainDependencies
        ): LoadingDomainFactory {
            return LoadingDomainFactory(dependencies)
        }

        @Provides
        @LoadingFeatureScope
        fun provideLoadingDomainApi(
            loadingDomainFactory: LoadingDomainFactory
        ): LoadingDomain {
            return loadingDomainFactory.create()
        }

        @Provides
        fun provideLoadingStore(
            loadingDomain: LoadingDomain
        ): LoadingStore {
            return loadingDomain.getStore()
        }

        @Provides
        fun provideInitialLoadingStoreState(
            loadingDomain: LoadingDomain
        ): LoadingStore.State {
            return loadingDomain.getLoadingStoreInitialState()
        }
    }
}