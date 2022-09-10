package application.di.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import application.di.component.scope.ApplicationScope
import application.di.modules.coroutines.CoroutinesModule
import application.di.modules.features.FeaturesModule
import application.di.modules.mvikotlin.MviKotlinModule
import dagger.Component
import features.home.di.component.dependencies.HomeFeatureDependencies

@ApplicationScope
@Component(
    modules = [
        CoroutinesModule::class,
        MviKotlinModule::class,
        FeaturesModule::class
    ]
)
internal interface ApplicationComponent : HomeFeatureDependencies {
    fun getHomeFeatureDependencies(): HomeFeatureDependencies

    @Component.Factory
    interface Factory {
        fun create(): ApplicationComponent
    }

    companion object {
        operator fun invoke(): ApplicationComponent {
            return DaggerApplicationComponent.factory()
                .create()
        }
    }
}

@Composable
internal fun rememberApplicationComponent(): ApplicationComponent {
    return remember { ApplicationComponent() }
}