package com.lauruscorp.features.example.examplefeature.di.component

import com.lauruscorp.features.core.di.FeatureComponent
import com.lauruscorp.features.core.di.qualifiers.FeatureIdQualifier
import com.lauruscorp.features.example.exampledomain.api.ExampleDomainDependencies
import com.lauruscorp.features.example.examplefeature.api.ExampleFeatureApi
import com.lauruscorp.features.example.examplefeature.api.ExampleFeatureDependencies
import com.lauruscorp.features.example.examplefeature.di.component.scope.ExampleFeatureScope
import com.lauruscorp.features.example.examplefeature.di.modules.domains.DomainsModule
import com.lauruscorp.features.example.examplefeature.di.modules.features.FeaturesModule
import com.lauruscorp.features.example.examplefeature.di.modules.subcomponents.SubcomponentsModule
import com.lauruscorp.features.example.examplefeature.di.modules.uilaunchers.UiLaunchersModule
import com.lauruscorp.features.example.examplefeature.presentation.di.component.ExamplePresentationComponent
import dagger.BindsInstance
import dagger.Component

@ExampleFeatureScope
@Component(
	dependencies = [
		ExampleFeatureDependencies::class
	],
	modules = [
		SubcomponentsModule::class,
		UiLaunchersModule::class,
		DomainsModule::class,
		FeaturesModule::class
	]
)
internal interface ExampleFeatureComponent : FeatureComponent, ExampleFeatureApi, ExampleDomainDependencies {
	fun getExamplePresentationComponentFactory(): ExamplePresentationComponent.Factory
	
	@Component.Factory
	interface Factory {
		fun create(
			@BindsInstance @FeatureIdQualifier featureId: Long,
			dependencies: ExampleFeatureDependencies
		): ExampleFeatureComponent
	}
	
	companion object {
		fun create(
			featureId: Long,
			dependencies: ExampleFeatureDependencies
		): ExampleFeatureComponent {
			return DaggerExampleFeatureComponent.factory()
				.create(featureId, dependencies)
		}
	}
}