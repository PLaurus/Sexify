package com.lauruscorp.sexifyapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lauruscorp.sexifyapp.R
import com.lauruscorp.sexifyapp.di.component.SexifyComponent

internal class SexifyActivity : AppCompatActivity(R.layout.activity_sex_assistant) {
	override fun onCreate(savedInstanceState: Bundle?) {
		injectDependencies()
		super.onCreate(savedInstanceState)
	}
	
	private fun injectDependencies() {
		SexifyComponent.create()
			.inject(this)
	}
}