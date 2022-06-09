package com.lauruscorp.examplefeature.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textview.MaterialTextView
import com.lauruscorp.examplefeature.ExampleFeatureComponentsRegistry
import com.lauruscorp.examplefeature.R
import com.lauruscorp.examplefeature.di.qualifiers.ExampleTextDependencyQualifier
import javax.inject.Inject

internal class ExampleFeatureActivity : AppCompatActivity(R.layout.activity_example_feature) {
	private val testMaterialTextView: MaterialTextView by lazy { findViewById(R.id.testMaterialTextView) }
	
	@Inject @ExampleTextDependencyQualifier lateinit var testText: String
	
	override fun onCreate(savedInstanceState: Bundle?) {
		injectDependencies()
		super.onCreate(savedInstanceState)
		initializeTestMaterialTextView()
	}
	
	private fun injectDependencies() {
		val intent = this.intent
		
		if (!intent.hasExtra(FEATURE_ID_KEY)) {
			throw IllegalStateException("${ExampleFeatureActivity::class::simpleName} is not provided with feature id")
		}
		
		val featureId = intent.getLongExtra(FEATURE_ID_KEY, 0)
		
		ExampleFeatureComponentsRegistry[featureId]
			.inject(this)
	}
	
	private fun initializeTestMaterialTextView() {
		testMaterialTextView.text = testText
	}
	
	companion object {
		const val FEATURE_ID_KEY = "FEATURE_ID_KEY"
	}
}