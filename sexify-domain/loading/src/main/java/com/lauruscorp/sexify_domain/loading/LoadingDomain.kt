package com.lauruscorp.sexify_domain.loading

import com.lauruscorp.core_jvm.di.dagger.qualifiers.mvi.InitialStateQualifier
import com.lauruscorp.sexify_domain.loading.store.LoadingStore

interface LoadingDomain {
    fun getStore(): LoadingStore

    @InitialStateQualifier
    fun getLoadingStoreInitialState(): LoadingStore.State
}