package com.lauruscorp.sexifyapp.features.game.api.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.lauruscorp.core.ui.ViewBindingUi
import com.lauruscorp.sexifyapp.R
import com.lauruscorp.sexifyapp.databinding.LayoutGameBinding
import com.lauruscorp.sexifyapp.features.game.api.GameFeatureDependencies
import com.lauruscorp.sexifyapp.features.game.di.component.GameFeatureComponent
import javax.inject.Inject

class GameFragment(
	private val dependencies: GameFeatureDependencies
) : Fragment(R.layout.layout_game) {
	@Inject
	lateinit var ui: ViewBindingUi<LayoutGameBinding>
	
	override fun onAttach(context: Context) {
		super.onAttach(context)
		injectDependencies()
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		ui.bindToViewLifecycleOwner(
			view = view,
			lifecycle = viewLifecycleOwner.lifecycle
		)
	}
	
	private fun injectDependencies() {
		GameFeatureComponent(
			dependencies = dependencies,
			viewModelStoreOwner = this
		).inject(this)
	}
}