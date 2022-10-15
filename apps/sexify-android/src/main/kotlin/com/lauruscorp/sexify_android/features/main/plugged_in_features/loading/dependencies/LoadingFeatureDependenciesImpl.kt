package com.lauruscorp.sexify_android.features.main.plugged_in_features.loading.dependencies

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.lauruscorp.core_jvm.coroutines.CoroutineDispatchers
import com.lauruscorp.sexify_android.features.loading.dependencies.LoadingFeatureDependencies
import com.lauruscorp.sexify_domain.loading.initializer.ExternalInitializer
import javax.inject.Inject

internal class LoadingFeatureDependenciesImpl @Inject constructor(
    private val storeFactory: StoreFactory,
    private val coroutineDispatchers: CoroutineDispatchers,
    private val externalInitializer: ExternalInitializer
) : LoadingFeatureDependencies {
    override fun getStoreFactory(): StoreFactory = storeFactory
    override fun getCoroutineDispatchers(): CoroutineDispatchers = coroutineDispatchers
    override fun getInitializer(): ExternalInitializer = externalInitializer
}