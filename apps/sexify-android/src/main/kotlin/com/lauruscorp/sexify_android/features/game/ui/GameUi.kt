package com.lauruscorp.sexify_android.features.game.ui

import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.lauruscorp.core_android.ui.ViewBindingUi
import com.lauruscorp.sexify_android.databinding.LayoutGameBinding
import javax.inject.Inject

internal class GameUi @Inject constructor(
    viewBindingProvider: @JvmSuppressWildcards (View) -> LayoutGameBinding
) : ViewBindingUi<LayoutGameBinding>(viewBindingProvider) {
    override fun onBound(viewBinding: LayoutGameBinding, lifecycleOwner: LifecycleOwner) {
        super.onBound(viewBinding, lifecycleOwner)
        viewBinding.run {

        }
    }
}