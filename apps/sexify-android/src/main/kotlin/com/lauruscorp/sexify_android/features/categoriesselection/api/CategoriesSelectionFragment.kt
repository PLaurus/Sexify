package com.lauruscorp.sexify_android.features.categoriesselection.api

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.lauruscorp.core_android.ui.ViewBindingUi
import com.lauruscorp.sexify_android.R
import com.lauruscorp.sexify_android.databinding.LayoutCategoriesSelectionBinding
import com.lauruscorp.sexify_android.features.categoriesselection.di.component.CategoriesSelectionFeatureComponent
import javax.inject.Inject

class CategoriesSelectionFragment(
    private val dependencies: CategoriesSelectionFeatureDependencies
) : Fragment(R.layout.layout_categories_selection) {
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
        CategoriesSelectionFeatureComponent(
            dependencies = dependencies,
            viewModelStoreOwner = this
        ).inject(this)
    }
}