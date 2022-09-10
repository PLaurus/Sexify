package application.di.modules.features

import application.di.modules.features.home.HomeFeatureModule
import dagger.Module

@Module(
    includes = [
        HomeFeatureModule::class
    ]
)
internal interface FeaturesModule