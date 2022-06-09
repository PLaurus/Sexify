package com.lauruscorp.sexassistant.di.modules

import com.lauruscorp.examplefeature.ExampleFeatureFactory
import com.lauruscorp.examplefeature.di.qualifiers.ExampleTextDependencyQualifier
import com.lauruscorp.sexassistant.di.SexAssistantComponent
import dagger.Module
import dagger.Provides

@Module
internal class ExampleFeatureModule {
	@Provides
	@ExampleTextDependencyQualifier
	fun provideExampleTextDependency(): String {
		return "Text is provided by ${SexAssistantComponent::class.simpleName}"
	}
	
	@Provides
	fun provideExampleFeatureFactory(component: SexAssistantComponent): ExampleFeatureFactory {
		return ExampleFeatureFactory(component)
	}
}