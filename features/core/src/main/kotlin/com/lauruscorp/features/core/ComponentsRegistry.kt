package com.lauruscorp.features.core

import com.lauruscorp.features.core.analytics.ComponentsRegistryAnalytics
import com.lauruscorp.features.core.di.FeatureComponent
import java.util.WeakHashMap

abstract class ComponentsRegistry<FeatureComponentT : FeatureComponent> {
	private val aliveComponents = WeakHashMap<FeatureComponentT, Long>()
	private val hardReferences = mutableListOf<FeatureComponentT>()
	private val componentsRegistryAnalytics = ComponentsRegistryAnalytics(
		aliveComponents = aliveComponents,
		hardReferences = hardReferences,
		logTag = this::class.java.simpleName
	)
	
	init {
		componentsRegistryAnalytics.start()
	}
	
	@Synchronized
	operator fun get(featureId: Long): FeatureComponentT {
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
	fun createAndRegister(
		componentFactory: () -> FeatureComponentT
	): FeatureComponentT {
		componentsRegistryAnalytics.manuallyLogComponentsRegistryState(description = "Before ${::createAndRegister.name}.")
		
		val component = componentFactory()
		
		aliveComponents[component] = component.getFeatureId()
		
		componentsRegistryAnalytics.manuallyLogComponentsRegistryState(description = "After ${::createAndRegister.name}.")
		
		return component
	}
	
	fun registerComponentHardReference(component: FeatureComponentT) {
		componentsRegistryAnalytics.manuallyLogComponentsRegistryState(description = "Before ${::registerComponentHardReference.name}.")
		
		hardReferences.add(component)
		
		componentsRegistryAnalytics.manuallyLogComponentsRegistryState(description = "After ${::registerComponentHardReference.name}.")
	}
}