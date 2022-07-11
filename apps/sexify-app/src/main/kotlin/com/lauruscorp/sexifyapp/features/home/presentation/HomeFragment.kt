package com.lauruscorp.sexifyapp.features.home.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.lauruscorp.core.ui.ViewBindingUi
import com.lauruscorp.sexifyapp.R
import com.lauruscorp.sexifyapp.databinding.LayoutHomeBinding
import javax.inject.Inject

internal class HomeFragment : Fragment(R.layout.layout_home) {
	@Inject
	lateinit var ui: ViewBindingUi<LayoutHomeBinding>
	
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
	}
}