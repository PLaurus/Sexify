package com.lauruscorp.features.example.exampleapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lauruscorp.core.android.contentView
import com.lauruscorp.core.ui.ViewBindingUi
import com.lauruscorp.features.example.exampleapp.R
import com.lauruscorp.features.example.exampleapp.activity.di.component.ExampleActivityComponent
import com.lauruscorp.features.example.exampleapp.application.ExampleApplication
import com.lauruscorp.features.example.exampleapp.databinding.LayoutExampleAppBinding
import javax.inject.Inject

internal class ExampleAppActivity : AppCompatActivity(R.layout.layout_example_app) {
	@Inject
	lateinit var ui: ViewBindingUi<LayoutExampleAppBinding>
	
	override fun onCreate(savedInstanceState: Bundle?) {
		injectDependencies()
		super.onCreate(savedInstanceState)
		ui.bindToViewLifecycleOwner(
			view = contentView!!,
			lifecycle = lifecycle
		)
	}
	
	private fun injectDependencies() {
		ExampleActivityComponent(
			exampleApplicationComponent = (application as ExampleApplication).exampleApplicationComponent,
			viewModelStoreOwner = this
		).inject(this)
	}
}