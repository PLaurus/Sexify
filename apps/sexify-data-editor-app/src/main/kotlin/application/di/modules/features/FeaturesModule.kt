package application.di.modules.features

import application.features.home.di.HomeFeatureModule
import application.features.loading.di.LoadingFeatureModule
import application.features.task_editor.di.TaskEditorFeatureModule
import dagger.Module

@Module(
    includes = [
        LoadingFeatureModule::class,
        HomeFeatureModule::class,
        TaskEditorFeatureModule::class
    ]
)
internal interface FeaturesModule