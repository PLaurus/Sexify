package com.lauruscorp.examplefeature.di.modules

import com.lauruscorp.examplefeature.ExampleFeatureUiLauncher
import com.lauruscorp.examplefeature.ExampleFeatureUiLauncherImpl
import dagger.Binds
import dagger.Module

@Module
internal interface FeatureModule {
	@Binds
	fun bindExampleFeatureUiLauncher(
		uiLauncher: ExampleFeatureUiLauncherImpl
	): ExampleFeatureUiLauncher
}