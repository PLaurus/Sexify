package com.lauruscorp.examplefeature

import com.lauruscorp.examplefeature.di.ExampleFeatureComponent
import com.lauruscorp.examplefeature.di.dependencies.ExampleFeatureDependencies
import java.util.WeakHashMap

internal object ExampleFeatureComponentsRegistry {
	private val aliveComponents = WeakHashMap<ExampleFeatureComponent, Long>()
	private val hardReferences = mutableListOf<ExampleFeatureComponent>()
	
	@Synchronized
	operator fun get(featureId: Long): ExampleFeatureComponent {
		val component = aliveComponents.keys
			.firstOrNull { it.getFeatureId() == featureId }
			?: hardReferences
				.firstOrNull { it.getFeatureId() == featureId }
		
		if (component != null) {
			hardReferences.remove(component)
		}
		
		return component
			?: throw IllegalStateException("Component with id $featureId is not created!")
	}
	
	@Synchronized
	fun create(dependencies: ExampleFeatureDependencies): ExampleFeatureComponent {
		val component = ExampleFeatureComponent
			.create(
				featureId = System.currentTimeMillis(),
				dependencies = dependencies
			)
		
		aliveComponents[component] = component.getFeatureId()
		
		return component
	}
	
	fun registerComponentHardReference(component: ExampleFeatureComponent) {
		hardReferences.add(component)
	}
}