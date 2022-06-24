package com.lauruscorp.examplefeature.presentation.uilauncher

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import com.lauruscorp.core.android.application.SimpleActivityLifecycleCallbacks
import com.lauruscorp.examplefeature.api.ExampleFeatureUiLauncher
import com.lauruscorp.examplefeature.di.component.ExampleFeatureComponent
import com.lauruscorp.examplefeature.di.component.ExampleFeatureComponentsRegistry
import com.lauruscorp.examplefeature.presentation.ExampleActivity
import javax.inject.Inject

internal class ExampleUiLauncher @Inject constructor(
	private val exampleFeatureComponent: ExampleFeatureComponent
) : ExampleFeatureUiLauncher {
	override fun launchUi(application: Application, context: Context) {
		ExampleFeatureComponentsRegistry.registerComponent(exampleFeatureComponent)
		
		val intent = Intent(context, ExampleActivity::class.java).apply {
			val featureId = exampleFeatureComponent.getFeatureId()
			putExtra(ExampleActivity.FEATURE_ID_KEY, featureId)
		}
		
		context.startActivity(intent)
		
		application.registerActivityLifecycleCallbacks(object : SimpleActivityLifecycleCallbacks {
			override fun onActivityDestroyed(activity: Activity) {
				super.onActivityDestroyed(activity)
				
				if (activity is ExampleActivity && activity.isFinishing) {
					ExampleFeatureComponentsRegistry.unregisterComponent(exampleFeatureComponent)
					application.unregisterActivityLifecycleCallbacks(this)
				}
			}
		})
	}
}