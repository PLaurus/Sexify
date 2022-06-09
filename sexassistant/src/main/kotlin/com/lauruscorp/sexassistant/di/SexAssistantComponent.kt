package com.lauruscorp.sexassistant.di

import com.lauruscorp.examplefeature.di.dependencies.ExampleFeatureDependencies
import com.lauruscorp.sexassistant.di.modules.ExampleFeatureModule
import com.lauruscorp.sexassistant.presentation.SexAssistantActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
	modules = [
		ExampleFeatureModule::class
	]
)
internal interface SexAssistantComponent : ExampleFeatureDependencies {
	@Component.Factory
	interface Factory {
		fun create(): SexAssistantComponent
	}
	
	fun inject(activity: SexAssistantActivity)
	
	companion object {
		fun create(): SexAssistantComponent {
			return DaggerSexAssistantComponent.factory()
				.create()
		}
	}
}