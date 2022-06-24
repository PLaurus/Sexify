package com.lauruscorp.features.example.examplefeature.di.modules.uilaunchers

import com.lauruscorp.features.example.examplefeature.api.ExampleFeatureUiLauncher
import com.lauruscorp.features.example.examplefeature.presentation.uilauncher.ExampleUiLauncher
import dagger.Binds
import dagger.Module

@Module
internal interface UiLaunchersModule {
	@Binds
	fun provideExampleFeatureUiLauncher(
		uiLauncher: ExampleUiLauncher
	): ExampleFeatureUiLauncher
}