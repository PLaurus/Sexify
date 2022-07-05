package com.lauruscorp.exampleapp.application.di.modules.features

import com.lauruscorp.exampleapp.application.di.modules.features.example2feature.Example2FeatureModule
import com.lauruscorp.exampleapp.application.di.modules.features.examplefeature.ExampleFeatureModule
import dagger.Module

@Module(
	includes = [
		ExampleFeatureModule::class,
		Example2FeatureModule::class
	]
)
internal interface FeaturesModule