package com.lauruscorp.examplefeature

import com.lauruscorp.examplefeature.analytics.ComponentsRegistryAnalytics
import com.lauruscorp.examplefeature.di.ExampleFeatureComponent
import com.lauruscorp.examplefeature.di.dependencies.ExampleFeatureDependencies
import java.util.WeakHashMap

internal object ExampleFeatureComponentsRegistry {
	private val aliveComponents = WeakHashMap<ExampleFeatureComponent, Long>()
	private val hardReferences = mutableListOf<ExampleFeatureComponent>()
	private val componentsRegistryAnalytics = ComponentsRegistryAnalytics(
		aliveComponents = aliveComponents,
		hardReferences = hardReferences,
		logTag = ExampleFeatureComponentsRegistry::class.java.simpleName
	)
	
	init {
		componentsRegistryAnalytics.start()
	}
	
	@Synchronized
	operator fun get(featureId: Long): ExampleFeatureComponent {
		componentsRegistryAnalytics.manuallyLogComponentsRegistryState(description = "Before ${::get.name}.")
		
		val component = aliveComponents.keys
			.firstOrNull { it.getFeatureId() == featureId }
			?: hardReferences
				.firstOrNull { it.getFeatureId() == featureId }
		
		if (component != null) {
			hardReferences.remove(component)
		}
		
		componentsRegistryAnalytics.manuallyLogComponentsRegistryState(description = "After ${::get.name}.")
		
		return component
			?: throw IllegalStateException("Component with id $featureId is not created!")
	}
	
	@Synchronized
	fun create(dependencies: ExampleFeatureDependencies): ExampleFeatureComponent {
		componentsRegistryAnalytics.manuallyLogComponentsRegistryState(description = "Before ${::create.name}.")
		
		val component = ExampleFeatureComponent
			.create(
				featureId = System.currentTimeMillis(),
				dependencies = dependencies
			)
		
		aliveComponents[component] = component.getFeatureId()
		
		componentsRegistryAnalytics.manuallyLogComponentsRegistryState(description = "After ${::create.name}.")
		
		return component
	}
	
	fun registerComponentHardReference(component: ExampleFeatureComponent) {
		componentsRegistryAnalytics.manuallyLogComponentsRegistryState(description = "Before ${::registerComponentHardReference.name}.")
		
		hardReferences.add(component)
		
		componentsRegistryAnalytics.manuallyLogComponentsRegistryState(description = "After ${::registerComponentHardReference.name}.")
	}
}