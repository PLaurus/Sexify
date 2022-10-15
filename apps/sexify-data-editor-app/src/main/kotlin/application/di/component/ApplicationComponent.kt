package application.di.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import application.di.component.scope.ApplicationScope
import application.di.modules.coroutines.CoroutinesModule
import application.di.modules.data_sources.DataSourcesModule
import application.di.modules.databases.DatabasesModule
import application.di.modules.features.FeaturesModule
import application.di.modules.mvikotlin.MviKotlinModule
import application.di.modules.repositories.RepositoriesModule
import dagger.Component
import features.home.dependencies.HomeFeatureDependencies
import features.loading.dependencies.LoadingFeatureDependencies
import features.task_editor.dependencies.TaskEditorFeatureDependencies

@ApplicationScope
@Component(
    modules = [
        CoroutinesModule::class,
        DatabasesModule::class,
        DataSourcesModule::class,
        RepositoriesModule::class,
        MviKotlinModule::class,
        FeaturesModule::class
    ]
)
internal interface ApplicationComponent {
    fun getHomeFeatureDependencies(): HomeFeatureDependencies
    fun getLoadingFeatureDependencies(): LoadingFeatureDependencies
    fun getTaskEditorFeatureDependencies(): TaskEditorFeatureDependencies
    
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