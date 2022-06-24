package com.lauruscorp.exampleapp.application.di.modules.subcomponents

import com.lauruscorp.exampleapp.activity.di.component.ExampleActivityComponent
import dagger.Module

@Module(
	subcomponents = [
		ExampleActivityComponent::class
	]
)
internal interface SubcomponentsModule