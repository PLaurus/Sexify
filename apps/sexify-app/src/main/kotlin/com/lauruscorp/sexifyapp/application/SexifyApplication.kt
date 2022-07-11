package com.lauruscorp.sexifyapp.application

import android.app.Application
import com.lauruscorp.sexifyapp.application.di.component.SexifyApplicationComponent

internal class SexifyApplication : Application() {
	val sexifyApplicationComponent: SexifyApplicationComponent by lazy {
		SexifyApplicationComponent(application = this)
	}
	
	override fun onCreate() {
		injectDependencies()
		super.onCreate()
	}
	
	private fun injectDependencies() {
		sexifyApplicationComponent.inject(this)
	}
}