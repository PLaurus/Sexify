package com.lauruscorp.sexify_android.features.main.plugged_in_features.couple_names_editor.dependencies

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.lauruscorp.core.coroutines.CoroutineDispatchers
import com.lauruscorp.features.couplenameseditordomain.dependencies.data.PlayersProvider
import com.lauruscorp.sexify_android.features.couplenameseditor.dependencies.CoupleNamesEditorFeatureDependencies
import javax.inject.Inject

internal class CoupleNamesEditorFeatureDependenciesImpl @Inject constructor(
    private val storeFactory: StoreFactory,
    private val coroutineDispatchers: CoroutineDispatchers,
    private val playersProvider: PlayersProvider
) : CoupleNamesEditorFeatureDependencies {
    override fun getStoreFactory(): StoreFactory = storeFactory
    override fun getCoroutineDispatchers(): CoroutineDispatchers = coroutineDispatchers
    override fun getPlayersProvider(): PlayersProvider = playersProvider
}