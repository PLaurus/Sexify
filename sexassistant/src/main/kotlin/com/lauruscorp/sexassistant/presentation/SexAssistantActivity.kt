package com.lauruscorp.sexassistant.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lauruscorp.sexassistant.R
import com.lauruscorp.sexassistant.di.component.SexAssistantComponent

internal class SexAssistantActivity : AppCompatActivity(R.layout.activity_sex_assistant) {
	override fun onCreate(savedInstanceState: Bundle?) {
		injectDependencies()
		super.onCreate(savedInstanceState)
	}
	
	private fun injectDependencies() {
		SexAssistantComponent.create()
			.inject(this)
	}
}