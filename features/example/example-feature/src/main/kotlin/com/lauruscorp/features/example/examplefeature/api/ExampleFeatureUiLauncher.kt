package com.lauruscorp.features.example.examplefeature.api

import android.app.Application
import android.content.Context

interface ExampleFeatureUiLauncher {
	fun launchUi(application: Application, context: Context)
}