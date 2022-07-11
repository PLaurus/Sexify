package com.lauruscorp.sexifyapp.features.categoriesselection.ui

import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.lauruscorp.core.ui.ViewBindingUi
import com.lauruscorp.sexifyapp.databinding.LayoutCategoriesSelectionBinding
import javax.inject.Inject

internal class CategoriesSelectionUi @Inject constructor(
	viewBindingProvider: @JvmSuppressWildcards (View) -> LayoutCategoriesSelectionBinding
) : ViewBindingUi<LayoutCategoriesSelectionBinding>(viewBindingProvider) {
	override fun onBound(viewBinding: LayoutCategoriesSelectionBinding, lifecycleOwner: LifecycleOwner) {
		super.onBound(viewBinding, lifecycleOwner)
		viewBinding.run {
		
		}
	}
}