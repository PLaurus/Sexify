package application.features.loading.dependencies

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.lauruscorp.core_jvm.coroutines.CoroutineDispatchers
import features.loading.dependencies.LoadingFeatureDependencies
import features.loading.domain.initializers.Initializer
import javax.inject.Inject

internal class LoadingFeatureDependenciesImpl @Inject constructor(
	private val storeFactory: StoreFactory,
	private val coroutineDispatchers: CoroutineDispatchers,
	private val initializers: Set<@JvmSuppressWildcards Initializer>
) : LoadingFeatureDependencies {
	override fun getStoreFactory(): StoreFactory = storeFactory
	override fun getCoroutineDispatchers(): CoroutineDispatchers = coroutineDispatchers
	override fun getInitializers(): Set<Initializer> = initializers
}