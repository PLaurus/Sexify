package com.lauruscorp.sexassistant.di.modules.features

import com.lauruscorp.sexassistant.di.modules.features.examplefeature.ExampleFeatureModule
import dagger.Module

@Module(
	includes = [
		ExampleFeatureModule::class
	]
)
internal interface FeaturesModule