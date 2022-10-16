package com.lauruscorp.sexify_android.features.loading.domain.dependencies

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.lauruscorp.core.coroutines.CoroutineDispatchers
import com.lauruscorp.sexify_domain.loading.dependencies.LoadingDomainDependencies
import com.lauruscorp.sexify_domain.loading.initializer.ExternalInitializer
import javax.inject.Inject

internal class LoadingDomainDependenciesImpl @Inject constructor(
    private val storeFactory: StoreFactory,
    private val coroutinesDispatchers: CoroutineDispatchers,
    private val externalInitializer: ExternalInitializer
) : LoadingDomainDependencies {
    override fun getStoreFactory(): StoreFactory = storeFactory
    override fun getCoroutineDispatchers(): CoroutineDispatchers = coroutinesDispatchers
    override fun getInitializer(): ExternalInitializer = externalInitializer
}