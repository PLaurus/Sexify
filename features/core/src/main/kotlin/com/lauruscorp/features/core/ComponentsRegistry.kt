package com.lauruscorp.features.core

import com.lauruscorp.core.kotlin.compatRemoveIf
import com.lauruscorp.features.core.analytics.ComponentsRegistryAnalytics
import com.lauruscorp.features.core.di.FeatureComponent

abstract class ComponentsRegistry<ComponentT : FeatureComponent> {
	private val hardReferences = mutableListOf<ComponentT>()
	private val componentsRegistryAnalytics = ComponentsRegistryAnalytics(
		hardReferences = hardReferences,
		logTag = this::class.java.simpleName
	)
	
	init {
		componentsRegistryAnalytics.start()
	}
	
	operator fun get(featureId: Long): ComponentT {
		componentsRegistryAnalytics.manuallyLogComponentsRegistryState(description = "Before ${::get.name}.")
		
		val component = synchronized(hardReferences) {
			hardReferences.firstOrNull { it.getFeatureId() == featureId }
		} ?: throw IllegalStateException("Component with id $featureId is not created or dead!")
		
		componentsRegistryAnalytics.manuallyLogComponentsRegistryState(description = "After ${::get.name}.")
		
		return component
	}
	
	@Suppress("MemberVisibilityCanBePrivate")
	fun getAndUnregister(featureId: Long): ComponentT {
		componentsRegistryAnalytics.manuallyLogComponentsRegistryState(
			description = "Before ${::getAndUnregister.name}."
		)
		
		val component = get(featureId)
		
		synchronized(hardReferences) {
			hardReferences.remove(component)
		}
		
		componentsRegistryAnalytics.manuallyLogComponentsRegistryState(
			description = "After ${::getAndUnregister.name}."
		)
		
		return component
	}
	
	@Synchronized
	fun createAndRegister(
		createComponent: () -> ComponentT
	): ComponentT {
		componentsRegistryAnalytics.manuallyLogComponentsRegistryState(
			description = "Before ${::createAndRegister.name}."
		)
		
		val component = createComponent()
		
		synchronized(hardReferences) {
			hardReferences += component
		}
		
		componentsRegistryAnalytics.manuallyLogComponentsRegistryState(
			description = "After ${::createAndRegister.name}."
		)
		
		return component
	}
	
	fun registerComponent(component: ComponentT) {
		componentsRegistryAnalytics.manuallyLogComponentsRegistryState(
			description = "Before ${::registerComponent.name}."
		)
		
		synchronized(hardReferences) {
			hardReferences += component
		}
		
		componentsRegistryAnalytics.manuallyLogComponentsRegistryState(
			description = "After ${::registerComponent.name}."
		)
	}
	
	fun unregisterComponent(component: ComponentT) {
		componentsRegistryAnalytics.manuallyLogComponentsRegistryState(
			description = "Before ${::unregisterComponent.name}."
		)
		
		synchronized(hardReferences) {
			hardReferences.remove(component)
		}
		
		componentsRegistryAnalytics.manuallyLogComponentsRegistryState(
			description = "After ${::unregisterComponent.name}."
		)
	}
	
	@Suppress("MemberVisibilityCanBePrivate")
	fun unregisterComponentById(featureId: Long) {
		componentsRegistryAnalytics.manuallyLogComponentsRegistryState(
			description = "Before ${::unregisterComponentById.name}."
		)
		
		synchronized(hardReferences) {
			hardReferences.compatRemoveIf { it.getFeatureId() == featureId }
		}
		
		componentsRegistryAnalytics.manuallyLogComponentsRegistryState(
			description = "After ${::unregisterComponentById.name}."
		)
	}
}