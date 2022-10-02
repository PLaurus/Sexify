package com.lauruscorp.sexify_android.features.main.ui

import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.lauruscorp.core_android.ui.ViewBindingUi
import com.lauruscorp.sexify_android.databinding.LayoutMainBinding
import javax.inject.Inject

internal class MainUi @Inject constructor(
    viewBindingProvider: @JvmSuppressWildcards (View) -> LayoutMainBinding
) : ViewBindingUi<LayoutMainBinding>(viewBindingProvider) {
    override fun onBound(viewBinding: LayoutMainBinding, lifecycleOwner: LifecycleOwner) {
        super.onBound(viewBinding, lifecycleOwner)
        viewBinding.run {

        }
    }
}