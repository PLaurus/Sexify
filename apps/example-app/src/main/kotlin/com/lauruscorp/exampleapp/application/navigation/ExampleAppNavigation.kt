package com.lauruscorp.exampleapp.application.navigation

import android.content.Context

internal interface ExampleAppNavigation {
	fun launchExampleFeature(context: Context)
	fun launchExample2Feature(context: Context)
}