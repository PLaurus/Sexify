package com.lauruscorp.sexify_android.features.couplenameseditor.dependencies

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.lauruscorp.core.coroutines.CoroutineDispatchers
import com.lauruscorp.features.couplenameseditordomain.dependencies.data.PlayersProvider

interface CoupleNamesEditorFeatureDependencies {
    fun getStoreFactory(): StoreFactory
    fun getCoroutineDispatchers(): CoroutineDispatchers
    fun getPlayersProvider(): PlayersProvider
}