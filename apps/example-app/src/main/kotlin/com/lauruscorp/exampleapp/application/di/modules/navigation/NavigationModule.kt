package com.lauruscorp.exampleapp.application.di.modules.navigation

import com.lauruscorp.exampleapp.application.navigation.ExampleAppNavigation
import com.lauruscorp.exampleapp.application.navigation.ExampleAppNavigationImpl
import dagger.Binds
import dagger.Module

@Module
internal interface NavigationModule {
	@Binds
	fun provideExampleAppNavigation(
		exampleAppNavigation: ExampleAppNavigationImpl
	): ExampleAppNavigation
}