package com.lauruscorp.features.couplenameseditordomain.dependencies

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.lauruscorp.core_jvm.coroutines.CoroutineDispatchers
import com.lauruscorp.features.couplenameseditordomain.dependencies.data.PlayersProvider

interface ExternalDependencies {
    fun getStoreFactory(): StoreFactory
    fun getCoroutineDispatchers(): CoroutineDispatchers
    fun getPlayersProvider(): PlayersProvider
}