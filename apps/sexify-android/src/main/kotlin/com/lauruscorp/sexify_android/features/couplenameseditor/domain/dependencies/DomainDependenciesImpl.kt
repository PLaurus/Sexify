package com.lauruscorp.sexify_android.features.couplenameseditor.domain.dependencies

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.lauruscorp.core_jvm.coroutines.CoroutineDispatchers
import com.lauruscorp.features.couplenameseditordomain.dependencies.ExternalDependencies
import com.lauruscorp.features.couplenameseditordomain.dependencies.data.PlayersProvider
import javax.inject.Inject

internal class DomainDependenciesImpl @Inject constructor(
    private val storeFactory: StoreFactory,
    private val coroutinesDispatchers: CoroutineDispatchers,
    private val playersProvider: PlayersProvider
) : ExternalDependencies {
    override fun getStoreFactory(): StoreFactory = storeFactory
    override fun getCoroutineDispatchers(): CoroutineDispatchers = coroutinesDispatchers
    override fun getPlayersProvider(): PlayersProvider = playersProvider
}