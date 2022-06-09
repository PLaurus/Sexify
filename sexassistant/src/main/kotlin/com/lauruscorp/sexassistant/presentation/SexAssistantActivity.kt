package com.lauruscorp.sexassistant.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lauruscorp.examplefeature.ExampleFeatureFactory
import com.lauruscorp.sexassistant.R
import com.lauruscorp.sexassistant.di.SexAssistantComponent
import javax.inject.Inject

internal class SexAssistantActivity : AppCompatActivity(R.layout.activity_sex_assistant) {
	@Inject lateinit var exampleFeatureFactory: ExampleFeatureFactory
	
	override fun onCreate(savedInstanceState: Bundle?) {
		injectDependencies()
		super.onCreate(savedInstanceState)
		exampleFeatureFactory.create().launcher.launchUi(this)
	}
	
	private fun injectDependencies() {
		SexAssistantComponent.create()
			.inject(this)
	}
}