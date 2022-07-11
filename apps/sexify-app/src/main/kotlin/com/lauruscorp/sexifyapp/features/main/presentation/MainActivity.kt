package com.lauruscorp.sexifyapp.features.main.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lauruscorp.core.android.requireContentView
import com.lauruscorp.core.ui.ViewBindingUi
import com.lauruscorp.sexifyapp.R
import com.lauruscorp.sexifyapp.application.SexifyApplication
import com.lauruscorp.sexifyapp.databinding.LayoutMainBinding
import com.lauruscorp.sexifyapp.features.main.di.component.MainActivityComponent
import javax.inject.Inject

internal class MainActivity : AppCompatActivity(R.layout.layout_main) {
	@Inject
	lateinit var ui: ViewBindingUi<LayoutMainBinding>
	
	override fun onCreate(savedInstanceState: Bundle?) {
		injectDependencies()
		super.onCreate(savedInstanceState)
		ui.bindToViewLifecycleOwner(
			view = requireContentView,
			lifecycle = lifecycle
		)
	}
	
	private fun injectDependencies() {
		MainActivityComponent(
			sexifyApplicationComponent = (application as SexifyApplication).sexifyApplicationComponent,
			activityContext = this,
			viewModelStoreOwner = this
		).inject(this)
	}
}