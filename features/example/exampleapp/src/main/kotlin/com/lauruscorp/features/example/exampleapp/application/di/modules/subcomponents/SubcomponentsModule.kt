package com.lauruscorp.features.example.exampleapp.application.di.modules.subcomponents

import com.lauruscorp.features.example.exampleapp.activity.di.component.ExampleActivityComponent
import dagger.Module

@Module(
	subcomponents = [
		ExampleActivityComponent::class
	]
)
internal interface SubcomponentsModule