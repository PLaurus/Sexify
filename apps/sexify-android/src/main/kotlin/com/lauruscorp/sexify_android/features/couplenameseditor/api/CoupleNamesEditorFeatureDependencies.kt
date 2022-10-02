package com.lauruscorp.sexify_android.features.couplenameseditor.api

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.lauruscorp.core_jvm.coroutines.CoroutineDispatchers

interface CoupleNamesEditorFeatureDependencies {
    fun getStoreFactory(): StoreFactory
    fun getCoroutineDispatchers(): CoroutineDispatchers
}