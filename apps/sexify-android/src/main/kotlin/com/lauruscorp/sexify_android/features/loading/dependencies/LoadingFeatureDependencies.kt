package com.lauruscorp.sexify_android.features.loading.dependencies

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.lauruscorp.core.coroutines.CoroutineDispatchers
import com.lauruscorp.sexify_domain.loading.initializer.ExternalInitializer

interface LoadingFeatureDependencies {
    fun getStoreFactory(): StoreFactory
    fun getCoroutineDispatchers(): CoroutineDispatchers
    fun getInitializer(): ExternalInitializer
}