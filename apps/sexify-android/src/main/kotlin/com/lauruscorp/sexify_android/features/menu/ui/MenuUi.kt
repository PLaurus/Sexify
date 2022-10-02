package com.lauruscorp.sexify_android.features.menu.ui

import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.lauruscorp.core_android.ui.ViewBindingUi
import com.lauruscorp.sexify_android.databinding.LayoutMenuBinding
import javax.inject.Inject

internal class MenuUi @Inject constructor(
    viewBindingProvider: @JvmSuppressWildcards (View) -> LayoutMenuBinding
) : ViewBindingUi<LayoutMenuBinding>(viewBindingProvider) {
    override fun onBound(viewBinding: LayoutMenuBinding, lifecycleOwner: LifecycleOwner) {
        super.onBound(viewBinding, lifecycleOwner)
        viewBinding.run {

        }
    }
}