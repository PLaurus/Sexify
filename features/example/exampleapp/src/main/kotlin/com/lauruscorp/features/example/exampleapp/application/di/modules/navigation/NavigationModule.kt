package com.lauruscorp.features.example.exampleapp.application.di.modules.navigation

import com.lauruscorp.features.example.exampleapp.application.navigation.ExampleAppNavigation
import com.lauruscorp.features.example.exampleapp.application.navigation.ExampleAppNavigationImpl
import dagger.Binds
import dagger.Module

@Module
internal interface NavigationModule {
	@Binds
	fun provideExampleAppNavigation(
		exampleAppNavigation: ExampleAppNavigationImpl
	): ExampleAppNavigation
}