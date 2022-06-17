package com.lauruscorp.features.core.config

/**
 * Describes behavior of every feature.
 */
internal object FeaturesConfig {
	// Don't leave it true. Use it only for testing during feature development!
	const val isPeriodicalComponentsRegistryAnalyticsEnabled: Boolean = true
	const val componentsRegistryAnalyticsPeriodMs: Long = 60_000
	const val isManualComponentsRegistryAnalyticsEnabled: Boolean = true
}