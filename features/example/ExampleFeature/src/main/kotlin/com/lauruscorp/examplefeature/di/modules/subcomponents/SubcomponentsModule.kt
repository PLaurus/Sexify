package com.lauruscorp.examplefeature.di.modules.subcomponents

import com.lauruscorp.examplefeature.presentation.di.ExamplePresentationComponent
import dagger.Module

@Module(
	subcomponents = [
		ExamplePresentationComponent::class
	]
)
internal interface SubcomponentsModule