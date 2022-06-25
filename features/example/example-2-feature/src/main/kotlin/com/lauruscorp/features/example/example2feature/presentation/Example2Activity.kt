package com.lauruscorp.features.example.example2feature.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lauruscorp.core.android.requireContentView
import com.lauruscorp.core.ui.ViewBindingUi
import com.lauruscorp.features.example.example2feature.R
import com.lauruscorp.features.example.example2feature.databinding.LayoutExample2Binding
import com.lauruscorp.features.example.example2feature.presentation.di.component.Example2PresentationComponent
import javax.inject.Inject

internal class Example2Activity : AppCompatActivity(R.layout.layout_example_2) {
	@Inject lateinit var example2Ui: ViewBindingUi<LayoutExample2Binding>
	
	override fun onCreate(savedInstanceState: Bundle?) {
		injectDependencies()
		super.onCreate(savedInstanceState)
		example2Ui.bindToViewLifecycleOwner(
			view = requireContentView,
			lifecycle = lifecycle
		)
	}
	
	private fun injectDependencies() {
		Example2PresentationComponent(
			featureId = intent.getLongExtra(FEATURE_ID_KEY, 0),
			viewModelStoreOwner = this
		).inject(this)
	}
	
	companion object {
		const val FEATURE_ID_KEY = "FEATURE_ID_KEY"
	}
}