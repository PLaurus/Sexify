package features.home.di.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.lauruscorp.core.coroutines.CoroutineDispatchers
import com.lauruscorp.core.mvi.InitialStateQualifier
import dagger.Component
import features.home.dependencies.HomeFeatureDependencies
import features.home.di.component.scope.HomeFeatureScope
import features.home.di.modules.store.HomeStoreModule
import features.home.domain.store.HomeStore

@HomeFeatureScope
@Component(
    dependencies = [
        HomeFeatureDependencies::class
    ],
    modules = [
        HomeStoreModule::class,
    ]
)
internal interface HomeComponent {
    fun getHomeStore(): HomeStore

    @InitialStateQualifier
    fun getHomeStoreInitialState(): HomeStore.State

    fun getCoroutineDispatchers(): CoroutineDispatchers

    @Component.Factory
    interface Factory {
        fun create(
            dependencies: HomeFeatureDependencies
        ): HomeComponent
    }

    companion object {
        operator fun invoke(
            dependencies: HomeFeatureDependencies
        ): HomeComponent {
            return DaggerHomeComponent.factory()
                .create(dependencies = dependencies)
        }
    }
}

@Composable
internal fun rememberHomeComponent(
    dependencies: HomeFeatureDependencies
): HomeComponent {
    return remember { HomeComponent(dependencies) }
}