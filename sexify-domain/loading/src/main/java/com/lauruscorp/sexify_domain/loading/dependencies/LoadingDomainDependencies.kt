package com.lauruscorp.sexify_domain.loading.dependencies

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.lauruscorp.core_jvm.coroutines.CoroutineDispatchers
import com.lauruscorp.sexify_domain.loading.initializer.ExternalInitializer

interface LoadingDomainDependencies {
    fun getStoreFactory(): StoreFactory
    fun getCoroutineDispatchers(): CoroutineDispatchers
    fun getInitializer(): ExternalInitializer
}