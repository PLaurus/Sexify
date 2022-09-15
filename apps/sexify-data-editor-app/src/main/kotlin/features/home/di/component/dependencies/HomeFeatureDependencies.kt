package features.home.di.component.dependencies

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.lauruscorp.core_jvm.coroutines.CoroutineDispatchers
import com.lauruscorp.sexify_data.database.SexifyDatabase

interface HomeFeatureDependencies {
    fun getSexifyDatabase(): SexifyDatabase
    fun getStoreFactory(): StoreFactory
    fun getCoroutineDispatchers(): CoroutineDispatchers
}