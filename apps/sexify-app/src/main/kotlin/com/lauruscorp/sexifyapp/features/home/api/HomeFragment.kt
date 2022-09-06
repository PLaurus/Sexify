package com.lauruscorp.sexifyapp.features.home.api

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.lauruscorp.core_android.ui.ViewBindingUi
import com.lauruscorp.sexifyapp.R
import com.lauruscorp.sexifyapp.databinding.LayoutHomeBinding
import com.lauruscorp.sexifyapp.features.home.di.component.HomeFeatureComponent
import javax.inject.Inject

class HomeFragment(
    private val dependencies: HomeFeatureDependencies
) : Fragment(R.layout.layout_home) {
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
        HomeFeatureComponent(
            dependencies = dependencies,
            viewModelStoreOwner = this
        ).inject(this)
    }
}