package com.lauruscorp.sexify_domain.loading.di.component

import com.lauruscorp.sexify_domain.loading.LoadingDomain
import com.lauruscorp.sexify_domain.loading.dependencies.LoadingDomainDependencies
import com.lauruscorp.sexify_domain.loading.di.modules.store.LoadingStoreModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [
        LoadingDomainDependencies::class
    ],
    modules = [
        LoadingStoreModule::class
    ]
)
internal interface LoadingComponent : LoadingDomain {
    @Component.Factory
    interface Factory {
        fun create(
            dependencies: LoadingDomainDependencies
        ): LoadingComponent
    }

    companion object {
        operator fun invoke(
            dependencies: LoadingDomainDependencies
        ): LoadingComponent {
            return DaggerLoadingComponent.factory()
                .create(dependencies)
        }
    }
}