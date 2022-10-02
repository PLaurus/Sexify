package com.lauruscorp.sexify_android.application.di.modules.subcomponents

import com.lauruscorp.sexify_android.features.main.di.component.MainActivityComponent
import dagger.Module

@Module(
	subcomponents = [
		MainActivityComponent::class
	]
)
internal interface SubcomponentsModule