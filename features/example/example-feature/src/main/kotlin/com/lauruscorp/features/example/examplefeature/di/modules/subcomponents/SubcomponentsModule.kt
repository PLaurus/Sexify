package com.lauruscorp.features.example.examplefeature.di.modules.subcomponents

import com.lauruscorp.features.example.examplefeature.presentation.di.component.ExamplePresentationComponent
import dagger.Module

@Module(
	subcomponents = [
		ExamplePresentationComponent::class
	]
)
internal interface SubcomponentsModule