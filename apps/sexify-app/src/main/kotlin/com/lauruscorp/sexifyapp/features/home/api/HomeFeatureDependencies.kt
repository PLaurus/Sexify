package com.lauruscorp.sexifyapp.features.home.api

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.lauruscorp.core.coroutines.CoroutineDispatchers

interface HomeFeatureDependencies {
	fun getStoreFactory(): StoreFactory
	fun getCoroutineDispatchers(): CoroutineDispatchers
}