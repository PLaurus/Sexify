package com.lauruscorp.sexifyapp.di.modules.features

import com.lauruscorp.sexifyapp.di.modules.features.examplefeature.ExampleFeatureModule
import dagger.Module

@Module(
	includes = [
		ExampleFeatureModule::class
	]
)
internal interface FeaturesModule