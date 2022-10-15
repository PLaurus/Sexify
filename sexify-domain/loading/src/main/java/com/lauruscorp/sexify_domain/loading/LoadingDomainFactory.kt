package com.lauruscorp.sexify_domain.loading

import com.lauruscorp.sexify_domain.loading.dependencies.LoadingDomainDependencies
import com.lauruscorp.sexify_domain.loading.di.component.LoadingComponent

class LoadingDomainFactory(
    private val dependencies: LoadingDomainDependencies
) {
    fun create(): LoadingDomain {
        return LoadingComponent(
            dependencies = dependencies
        )
    }
}