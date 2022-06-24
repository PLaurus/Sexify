package com.lauruscorp.sexassistant.di.component

import com.lauruscorp.features.example.examplefeature.api.ExampleFeatureDependencies
import com.lauruscorp.sexassistant.di.modules.coroutines.CoroutinesModule
import com.lauruscorp.sexassistant.di.modules.features.FeaturesModule
import com.lauruscorp.sexassistant.di.modules.mvikotlin.MviKotlinModule
import com.lauruscorp.sexassistant.presentation.SexAssistantActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
	modules = [
		MviKotlinModule::class,
		CoroutinesModule::class,
		FeaturesModule::class
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