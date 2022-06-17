package com.lauruscorp.examplefeature.di

import com.lauruscorp.exampledomain.api.ExampleDomainDependencies
import com.lauruscorp.examplefeature.api.ExampleFeatureApi
import com.lauruscorp.examplefeature.api.ExampleFeatureDependencies
import com.lauruscorp.examplefeature.di.modules.domains.DomainsModule
import com.lauruscorp.examplefeature.di.modules.features.FeaturesModule
import com.lauruscorp.examplefeature.di.modules.subcomponents.SubcomponentsModule
import com.lauruscorp.examplefeature.di.modules.uilaunchers.UiLaunchersModule
import com.lauruscorp.examplefeature.di.scope.ExampleFeatureScope
import com.lauruscorp.examplefeature.presentation.di.ExamplePresentationComponent
import com.lauruscorp.features.core.di.FeatureComponent
import com.lauruscorp.features.core.di.qualifiers.FeatureIdQualifier
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