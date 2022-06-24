package com.lauruscorp.features.example.exampleapp.application.navigation

import android.app.Application
import android.content.Context
import com.lauruscorp.examplefeature.api.ExampleFeatureFactory
import com.lauruscorp.features.example.example2feature.api.Example2FeatureLauncher
import javax.inject.Inject

internal class ExampleAppNavigationImpl @Inject constructor(
	private val application: Application,
	private val exampleFeatureFactory: ExampleFeatureFactory,
	private val example2FeatureLauncher: Example2FeatureLauncher
) : ExampleAppNavigation {
	override fun launchExampleFeature(context: Context) {
		val exampleFeatureApi = exampleFeatureFactory.create()
		val exampleFeatureUiLauncher = exampleFeatureApi.launcher
		
		exampleFeatureUiLauncher.launchUi(
			application = application,
			context = context
		)
	}
	
	override fun launchExample2Feature(context: Context) {
		example2FeatureLauncher.start(
			application = application,
			context = context,
			onButtonClick = ::launchExampleFeature
		)
	}
}