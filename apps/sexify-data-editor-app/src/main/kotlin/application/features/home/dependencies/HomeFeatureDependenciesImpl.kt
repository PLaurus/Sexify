package application.features.home.dependencies

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.lauruscorp.core_jvm.coroutines.CoroutineDispatchers
import com.lauruscorp.sexify_data.database.SexifyDatabase
import dagger.Module
import features.home.di.component.dependencies.HomeFeatureDependencies
import javax.inject.Inject

@Module
internal class HomeFeatureDependenciesImpl @Inject constructor(
	private val sexifyDatabase: SexifyDatabase,
	private val storeFactory: StoreFactory,
	private val coroutinesDispatchers: CoroutineDispatchers
) : HomeFeatureDependencies {
	override fun getSexifyDatabase(): SexifyDatabase = sexifyDatabase
	
	override fun getStoreFactory(): StoreFactory = storeFactory
	
	override fun getCoroutineDispatchers(): CoroutineDispatchers = coroutinesDispatchers
}