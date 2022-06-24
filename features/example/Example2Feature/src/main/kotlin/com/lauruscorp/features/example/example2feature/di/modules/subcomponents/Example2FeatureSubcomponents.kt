package com.lauruscorp.features.example.example2feature.di.modules.subcomponents

import com.lauruscorp.features.example.example2feature.presentation.di.component.Example2PresentationComponent
import dagger.Module

@Module(
	subcomponents = [
		Example2PresentationComponent::class
	]
)
internal interface Example2FeatureSubcomponents