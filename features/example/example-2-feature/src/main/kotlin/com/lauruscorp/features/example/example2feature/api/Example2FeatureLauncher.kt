package com.lauruscorp.features.example.example2feature.api

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import com.lauruscorp.core.android.application.SimpleActivityLifecycleCallbacks
import com.lauruscorp.features.example.example2feature.di.component.Example2FeatureComponent
import com.lauruscorp.features.example.example2feature.di.component.Example2FeatureComponentsRegistry
import com.lauruscorp.features.example.example2feature.presentation.Example2Activity

class Example2FeatureLauncher(
    private val dependencies: Example2FeatureDependencies
) {
    fun start(
        application: Application,
        context: Context,
        onButtonClick: ((context: Context) -> Unit) = {},
    ) {
        val example2FeatureComponent = Example2FeatureComponentsRegistry.createAndRegister {
            Example2FeatureComponent(
                featureId = System.nanoTime(),
                dependencies = dependencies,
                onButtonClick = onButtonClick
            )
        }

        val intent = Intent(context, Example2Activity::class.java).apply {
            val featureId = example2FeatureComponent.getFeatureId()
            putExtra(Example2Activity.FEATURE_ID_KEY, featureId)
        }

        application.registerActivityLifecycleCallbacks(object : SimpleActivityLifecycleCallbacks {
            override fun onActivityDestroyed(activity: Activity) {
                super.onActivityDestroyed(activity)

                if (activity is Example2Activity && activity.isFinishing) {
                    Example2FeatureComponentsRegistry.unregisterComponent(example2FeatureComponent)
                    application.unregisterActivityLifecycleCallbacks(this)
                }
            }
        })

        context.startActivity(intent)
    }
}