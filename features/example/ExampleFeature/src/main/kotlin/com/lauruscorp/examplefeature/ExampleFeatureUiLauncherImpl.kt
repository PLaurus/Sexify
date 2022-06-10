package com.lauruscorp.examplefeature

import android.content.Context
import android.content.Intent
import com.lauruscorp.examplefeature.di.ExampleFeatureComponent
import com.lauruscorp.examplefeature.presentation.ExampleFeatureActivity
import com.lauruscorp.features.core.di.qualifiers.FeatureIdQualifier
import javax.inject.Inject

internal class ExampleFeatureUiLauncherImpl @Inject constructor(
	@FeatureIdQualifier private val featureId: Long,
	private val exampleFeatureComponent: ExampleFeatureComponent,
) : ExampleFeatureUiLauncher {
	override fun launchUi(context: Context) {
		ExampleFeatureComponentsRegistry.registerComponentHardReference(exampleFeatureComponent)
		
		val intent = Intent(context, ExampleFeatureActivity::class.java).apply {
			putExtra(ExampleFeatureActivity.FEATURE_ID_KEY, featureId)
		}
		
		context.startActivity(intent)
	}
}