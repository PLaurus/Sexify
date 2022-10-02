package com.lauruscorp.sexify_android.features.categoriesselection.api

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.lauruscorp.core_jvm.coroutines.CoroutineDispatchers

interface CategoriesSelectionFeatureDependencies {
    fun getStoreFactory(): StoreFactory
    fun getCoroutineDispatchers(): CoroutineDispatchers
}