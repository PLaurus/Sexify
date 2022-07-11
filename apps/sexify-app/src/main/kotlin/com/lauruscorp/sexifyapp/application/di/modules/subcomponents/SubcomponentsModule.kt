package com.lauruscorp.sexifyapp.application.di.modules.subcomponents

import com.lauruscorp.sexifyapp.features.main.di.component.MainActivityComponent
import dagger.Module

@Module(
	subcomponents = [
		MainActivityComponent::class
	]
)
internal interface SubcomponentsModule