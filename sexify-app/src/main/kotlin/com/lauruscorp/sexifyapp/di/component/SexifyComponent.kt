package com.lauruscorp.sexifyapp.di.component

import com.lauruscorp.features.example.examplefeature.api.ExampleFeatureDependencies
import com.lauruscorp.sexifyapp.di.modules.coroutines.CoroutinesModule
import com.lauruscorp.sexifyapp.di.modules.features.FeaturesModule
import com.lauruscorp.sexifyapp.di.modules.mvikotlin.MviKotlinModule
import com.lauruscorp.sexifyapp.presentation.SexifyActivity
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
internal interface SexifyComponent : ExampleFeatureDependencies {
	@Component.Factory
	interface Factory {
		fun create(): SexifyComponent
	}
	
	fun inject(activity: SexifyActivity)
	
	companion object {
		fun create(): SexifyComponent {
			return DaggerSexifyComponent.factory()
				.create()
		}
	}
}