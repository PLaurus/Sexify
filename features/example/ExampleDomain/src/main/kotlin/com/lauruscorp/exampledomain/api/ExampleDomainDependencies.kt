package com.lauruscorp.exampledomain.api

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.lauruscorp.core.coroutines.CoroutineDispatchers

interface ExampleDomainDependencies {
	fun getStoreFactory(): StoreFactory
	fun getCoroutineDispatchers(): CoroutineDispatchers
}