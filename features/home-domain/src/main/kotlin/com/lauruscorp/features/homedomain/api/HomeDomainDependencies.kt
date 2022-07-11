package com.lauruscorp.features.homedomain.api

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.lauruscorp.core.coroutines.CoroutineDispatchers

interface HomeDomainDependencies {
	fun getStoreFactory(): StoreFactory
	fun getCoroutineDispatchers(): CoroutineDispatchers
}