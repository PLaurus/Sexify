package com.lauruscorp.features.maindomain.api

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.lauruscorp.core.coroutines.CoroutineDispatchers

interface MainDomainDependencies {
	fun getStoreFactory(): StoreFactory
	fun getCoroutineDispatchers(): CoroutineDispatchers
}