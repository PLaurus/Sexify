package com.lauruscorp.examplefeature.di.modules.subcomponents

import com.lauruscorp.examplefeature.presentation.di.component.ExamplePresentationComponent
import dagger.Module

@Module(
	subcomponents = [
		ExamplePresentationComponent::class
	]
)
internal interface SubcomponentsModule