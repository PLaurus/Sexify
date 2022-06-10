package com.lauruscorp.examplefeature.analytics

import android.util.Log
import com.lauruscorp.examplefeature.config.ExampleFeatureConfig
import com.lauruscorp.examplefeature.di.ExampleFeatureComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

internal class ComponentsRegistryAnalytics(
	private val aliveComponents: Map<ExampleFeatureComponent, Long>,
	private val hardReferences: List<ExampleFeatureComponent>,
	private val checkPeriodMs: Long = 1_000,
	private val logTag: String = ComponentsRegistryAnalytics::class.java.simpleName
) {
	private val exampleFeatureConfig = ExampleFeatureConfig
	private var scope: CoroutineScope? = null
	
	@Synchronized
	fun start(stopAfterMs: Long = 60_000) {
		if (!canBeStarted()) {
			return
		}
		
		scope = CoroutineScope(context = Job()).apply {
			launch {
				while (isActive) {
					logComponentsRegistryState(description = "Periodical components registry check")
					delay(checkPeriodMs)
				}
			}
			
			launch {
				delay(stopAfterMs)
				stop()
			}
		}
	}
	
	@Synchronized
	fun stop() {
		scope?.cancel()
		scope = null
	}
	
	fun manuallyLogComponentsRegistryState(description: String? = null) {
		if (!exampleFeatureConfig.isManualComponentsRegistryAnalyticsEnabled) {
			return
		}
		
		logComponentsRegistryState(description)
	}
	
	private fun logComponentsRegistryState(
		description: String? = null
	) {
		val messageBuilder = StringBuilder(" ").apply {
			appendLine()
			description?.let { appendLine("=== $it ===") }
			appendLine("Current components registry state:")
			appendLine("aliveComponents:")
			
			aliveComponents.keys.forEachIndexed { index, aliveComponent ->
				appendLine("$index. Component id: ${aliveComponent.getFeatureId()}")
			}
			
			appendLine("hardReferences (unused components):")
			
			hardReferences.forEachIndexed { index, aliveComponent ->
				appendLine("$index. Component id: ${aliveComponent.getFeatureId()}")
			}
		}
		
		Log.i(logTag, messageBuilder.toString())
	}
	
	private fun canBeStarted(): Boolean {
		val scope = this.scope
		return exampleFeatureConfig.isPeriodicalComponentsRegistryAnalyticsEnabled && (scope == null || !scope.isActive)
	}
}