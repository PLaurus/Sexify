package com.lauruscorp.exampleapp.application.di.component

import android.app.Application
import com.lauruscorp.exampleapp.activity.di.component.ExampleActivityComponent
import com.lauruscorp.exampleapp.application.ExampleApplication
import com.lauruscorp.exampleapp.application.di.component.scope.ExampleApplicationScope
import com.lauruscorp.exampleapp.application.di.modules.application.ApplicationModule
import com.lauruscorp.exampleapp.application.di.modules.coroutines.CoroutinesModule
import com.lauruscorp.exampleapp.application.di.modules.features.FeaturesModule
import com.lauruscorp.exampleapp.application.di.modules.mvikotlin.MviKotlinModule
import com.lauruscorp.exampleapp.application.di.modules.navigation.NavigationModule
import com.lauruscorp.exampleapp.application.di.modules.subcomponents.SubcomponentsModule
import com.lauruscorp.features.example.example2feature.api.Example2FeatureDependencies
import com.lauruscorp.features.example.examplefeature.api.ExampleFeatureDependencies
import dagger.BindsInstance
import dagger.Component

@ExampleApplicationScope
@Component(
	modules = [
		ApplicationModule::class,
		SubcomponentsModule::class,
		MviKotlinModule::class,
		CoroutinesModule::class,
		NavigationModule::class,
		FeaturesModule::class
	]
)
internal interface ExampleApplicationComponent : ExampleFeatureDependencies, Example2FeatureDependencies {
	fun inject(application: ExampleApplication)
	fun getExampleActivityComponentFactory(): ExampleActivityComponent.Factory
	
	@Component.Factory
	interface Factory {
		fun create(
			@BindsInstance application: Application
		): ExampleApplicationComponent
	}
	
	companion object {
		operator fun invoke(
			application: Application
		): ExampleApplicationComponent {
			return DaggerExampleApplicationComponent.factory()
				.create(
					application = application
				)
		}
	}
}