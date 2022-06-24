package com.lauruscorp.features.core.analytics

import android.util.Log
import com.lauruscorp.features.core.config.FeaturesConfig
import com.lauruscorp.features.core.di.FeatureComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

internal class ComponentsRegistryAnalytics<ComponentT : FeatureComponent>(
	private val hardReferences: List<ComponentT>,
	private val checkPeriodMs: Long = 1_000,
	private val logTag: String = ComponentsRegistryAnalytics::class.java.simpleName
) {
	private val exampleFeatureConfig = FeaturesConfig
	private var scope: CoroutineScope? = null
	
	@Synchronized
	fun start(stopAfterMs: Long = FeaturesConfig.componentsRegistryAnalyticsPeriodMs) {
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
			appendLine("Current ComponentsRegistry state:")
			
			hardReferences.forEach { hardReference ->
				appendLine("\t${hardReference.getFeatureId()}: $hardReference")
			}
		}
		
		Log.i(logTag, messageBuilder.toString())
	}
	
	private fun canBeStarted(): Boolean {
		val scope = this.scope
		return exampleFeatureConfig.isPeriodicalComponentsRegistryAnalyticsEnabled && (scope == null || !scope.isActive)
	}
}