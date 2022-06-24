package com.lauruscorp.features.example.example2feature.di.component

import android.content.Context
import com.lauruscorp.exampledomain.api.ExampleDomainDependencies
import com.lauruscorp.features.core.di.FeatureComponent
import com.lauruscorp.features.core.di.qualifiers.FeatureIdQualifier
import com.lauruscorp.features.example.example2feature.api.Example2FeatureDependencies
import com.lauruscorp.features.example.example2feature.di.component.scope.Example2FeatureScope
import com.lauruscorp.features.example.example2feature.di.modules.domains.DomainsModule
import com.lauruscorp.features.example.example2feature.di.modules.features.FeaturesModule
import com.lauruscorp.features.example.example2feature.di.modules.subcomponents.Example2FeatureSubcomponents
import com.lauruscorp.features.example.example2feature.presentation.di.component.Example2PresentationComponent
import dagger.BindsInstance
import dagger.Component

@Example2FeatureScope
@Component(
	dependencies = [
		Example2FeatureDependencies::class
	],
	modules = [
		Example2FeatureSubcomponents::class,
		FeaturesModule::class,
		DomainsModule::class
	]
)
internal interface Example2FeatureComponent : FeatureComponent, ExampleDomainDependencies {
	fun getExample2PresentationComponentFactory(): Example2PresentationComponent.Factory
	
	@Component.Factory
	interface Factory {
		fun create(
			@BindsInstance @FeatureIdQualifier featureId: Long,
			dependencies: Example2FeatureDependencies,
			@BindsInstance onButtonClick: ((context: Context) -> Unit)
		): Example2FeatureComponent
	}
	
	companion object {
		fun create(
			featureId: Long,
			dependencies: Example2FeatureDependencies,
			onButtonClick: ((context: Context) -> Unit)
		): Example2FeatureComponent {
			return DaggerExample2FeatureComponent.factory()
				.create(
					featureId = featureId,
					dependencies = dependencies,
					onButtonClick = onButtonClick
				)
		}
	}
}