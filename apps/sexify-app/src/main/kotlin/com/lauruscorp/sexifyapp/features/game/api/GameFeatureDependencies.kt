package com.lauruscorp.sexifyapp.features.game.api

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.lauruscorp.core.coroutines.CoroutineDispatchers

interface GameFeatureDependencies {
	fun getStoreFactory(): StoreFactory
	fun getCoroutineDispatchers(): CoroutineDispatchers
}