package com.lauruscorp.sexify_android.features.main.plugged_in_features.menu.dependencies

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.lauruscorp.core.coroutines.CoroutineDispatchers
import com.lauruscorp.sexify_android.features.menu.api.MenuFeatureDependencies
import javax.inject.Inject

internal class MenuFeatureDependenciesImpl @Inject constructor(
	private val storeFactory: StoreFactory,
	private val coroutineDispatchers: CoroutineDispatchers
) : MenuFeatureDependencies {
	override fun getStoreFactory(): StoreFactory = storeFactory
	override fun getCoroutineDispatchers(): CoroutineDispatchers = coroutineDispatchers
}