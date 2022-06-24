package com.lauruscorp.examplefeature.di.modules.uilaunchers

import com.lauruscorp.examplefeature.api.ExampleFeatureUiLauncher
import com.lauruscorp.examplefeature.presentation.uilauncher.ExampleUiLauncher
import dagger.Binds
import dagger.Module

@Module
internal interface UiLaunchersModule {
	@Binds
	fun provideExampleFeatureUiLauncher(
		uiLauncher: ExampleUiLauncher
	): ExampleFeatureUiLauncher
}