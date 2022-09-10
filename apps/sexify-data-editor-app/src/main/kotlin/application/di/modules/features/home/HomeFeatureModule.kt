package application.di.modules.features.home

import application.di.component.ApplicationComponent
import dagger.Binds
import dagger.Module
import features.home.di.component.dependencies.HomeFeatureDependencies

@Module
internal abstract class HomeFeatureModule {
    @Binds
    abstract fun provideHomeFeatureDependencies(
        component: ApplicationComponent
    ): HomeFeatureDependencies
}