package com.lauruscorp.sexify_android.features.categoriesselection.ui

import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.lauruscorp.core_android.ui.ViewBindingUi
import com.lauruscorp.sexify_android.databinding.LayoutCategoriesSelectionBinding
import javax.inject.Inject

internal class CategoriesSelectionUi @Inject constructor(
    viewBindingProvider: @JvmSuppressWildcards (View) -> LayoutCategoriesSelectionBinding
) : ViewBindingUi<LayoutCategoriesSelectionBinding>(viewBindingProvider) {
    override fun onBound(
        viewBinding: LayoutCategoriesSelectionBinding,
        lifecycleOwner: LifecycleOwner
    ) {
        super.onBound(viewBinding, lifecycleOwner)
        viewBinding.run {

        }
    }
}