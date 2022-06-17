package com.lauruscorp.sexassistant.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.lauruscorp.examplefeature.api.ExampleFeatureFactory
import com.lauruscorp.sexassistant.R
import com.lauruscorp.sexassistant.di.component.SexAssistantComponent
import javax.inject.Inject

internal class SexAssistantActivity : AppCompatActivity(R.layout.activity_sex_assistant) {
	private val startExampleActivityButton: MaterialButton by lazy {
		findViewById(R.id.startExampleActivityButton)
	}
	
	@Inject lateinit var exampleFeatureFactory: ExampleFeatureFactory
	
	override fun onCreate(savedInstanceState: Bundle?) {
		injectDependencies()
		super.onCreate(savedInstanceState)
		initializeViews()
	}
	
	private fun injectDependencies() {
		SexAssistantComponent.create()
			.inject(this)
	}
	
	private fun initializeViews() {
		initializeStartExampleActivityButton()
	}
	
	private fun initializeStartExampleActivityButton() {
		startExampleActivityButton.setOnClickListener {
			exampleFeatureFactory.create().launcher.launchUi(application, this)
		}
	}
}