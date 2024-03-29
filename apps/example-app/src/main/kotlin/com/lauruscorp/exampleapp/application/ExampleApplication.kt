package com.lauruscorp.exampleapp.application

import android.app.Application
import com.lauruscorp.exampleapp.application.di.component.ExampleApplicationComponent

internal class ExampleApplication : Application() {
	val exampleApplicationComponent: ExampleApplicationComponent by lazy {
		ExampleApplicationComponent(application = this)
	}
	
	override fun onCreate() {
		injectDependencies()
		super.onCreate()
	}
	
	private fun injectDependencies() {
		exampleApplicationComponent.inject(this)
	}
}