package features.loading.dependencies

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.lauruscorp.core_jvm.coroutines.CoroutineDispatchers
import features.loading.domain.initializers.Initializer

interface LoadingFeatureDependencies {
	fun getStoreFactory(): StoreFactory
	fun getCoroutineDispatchers(): CoroutineDispatchers
	fun getInitializers(): Set<Initializer>
}