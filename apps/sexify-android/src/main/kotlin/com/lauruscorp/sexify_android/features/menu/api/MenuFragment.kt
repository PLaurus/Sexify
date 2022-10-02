package com.lauruscorp.sexify_android.features.menu.api

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.lauruscorp.core_android.ui.ViewBindingUi
import com.lauruscorp.sexify_android.R
import com.lauruscorp.sexify_android.databinding.LayoutMenuBinding
import com.lauruscorp.sexify_android.features.menu.di.component.MenuFeatureComponent
import javax.inject.Inject

class MenuFragment(
    private val dependencies: MenuFeatureDependencies
) : Fragment(R.layout.layout_menu) {
    @Inject
    lateinit var ui: ViewBindingUi<LayoutMenuBinding>

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
        MenuFeatureComponent(
            dependencies = dependencies,
            viewModelStoreOwner = this
        ).inject(this)
    }
}