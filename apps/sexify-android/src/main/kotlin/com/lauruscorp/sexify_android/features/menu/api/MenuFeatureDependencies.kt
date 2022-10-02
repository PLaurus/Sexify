package com.lauruscorp.sexify_android.features.menu.api

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.lauruscorp.core_jvm.coroutines.CoroutineDispatchers

interface MenuFeatureDependencies {
    fun getStoreFactory(): StoreFactory
    fun getCoroutineDispatchers(): CoroutineDispatchers
}