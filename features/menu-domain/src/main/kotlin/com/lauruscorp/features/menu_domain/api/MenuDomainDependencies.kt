package com.lauruscorp.features.menu_domain.api

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.lauruscorp.core_jvm.coroutines.CoroutineDispatchers

interface MenuDomainDependencies {
    fun getStoreFactory(): StoreFactory
    fun getCoroutineDispatchers(): CoroutineDispatchers
}