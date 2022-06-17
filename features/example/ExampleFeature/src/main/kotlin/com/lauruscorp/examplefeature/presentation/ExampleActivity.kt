package com.lauruscorp.examplefeature.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.lauruscorp.examplefeature.R
import com.lauruscorp.examplefeature.presentation.di.ExamplePresentationComponent
import com.lauruscorp.examplefeature.presentation.entities.TestObject
import com.lauruscorp.examplefeature.presentation.viewmodel.ExampleViewModel
import javax.inject.Inject

internal class ExampleActivity : AppCompatActivity(R.layout.activity_example_feature) {
	private val tag = this::class.java.simpleName
	private val testGcButton: MaterialButton by lazy { findViewById(R.id.testGcButton) }
	
	@Inject lateinit var viewModel: ExampleViewModel
	
	override fun onCreate(savedInstanceState: Bundle?) {
		injectDependencies()
		super.onCreate(savedInstanceState)
		initializeViews()
	}
	
	override fun onPause() {
		super.onPause()
		val isFinishing = this.isFinishing
		Log.i(tag, "onPause isFinishing: $isFinishing")
	}
	
	override fun onDestroy() {
		super.onDestroy()
		val isFinishing = this.isFinishing
		Log.i(tag, "onDestroy isFinishing: $isFinishing")
	}
	
	private fun initializeViews() {
		initializeTestGcButton()
	}
	
	private fun injectDependencies() {
		val intent = this.intent
		
		if (!intent.hasExtra(FEATURE_ID_KEY)) {
			throw IllegalStateException("${ExampleActivity::class::simpleName} is not provided with feature id")
		}
		
		ExamplePresentationComponent
			.create(
				featureId = intent.getLongExtra(FEATURE_ID_KEY, 0),
				viewModelStoreOwner = this
			)
			.inject(this)
		
		Log.i(tag, "Injected dependencies")
	}
	
	private fun initializeTestGcButton() {
		testGcButton.setOnClickListener {
			repeat(1_000_000) {
				TestObject(1000000000)
			}
		}
	}
	
	companion object {
		const val FEATURE_ID_KEY = "FEATURE_ID_KEY"
	}
}