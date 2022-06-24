package com.lauruscorp.examplefeature.api

import android.app.Application
import android.content.Context

interface ExampleFeatureUiLauncher {
	fun launchUi(application: Application, context: Context)
}