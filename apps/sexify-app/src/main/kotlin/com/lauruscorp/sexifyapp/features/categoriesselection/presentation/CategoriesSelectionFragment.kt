package com.lauruscorp.sexifyapp.features.categoriesselection.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.lauruscorp.core.ui.ViewBindingUi
import com.lauruscorp.sexifyapp.R
import com.lauruscorp.sexifyapp.databinding.LayoutCategoriesSelectionBinding
import javax.inject.Inject

internal class CategoriesSelectionFragment : Fragment(R.layout.layout_categories_selection) {
	@Inject
	lateinit var ui: ViewBindingUi<LayoutCategoriesSelectionBinding>
	
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