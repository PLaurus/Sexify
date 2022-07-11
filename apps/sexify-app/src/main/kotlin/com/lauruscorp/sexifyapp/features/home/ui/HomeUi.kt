package com.lauruscorp.sexifyapp.features.home.ui

import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.lauruscorp.core.ui.ViewBindingUi
import com.lauruscorp.sexifyapp.databinding.LayoutHomeBinding
import javax.inject.Inject

internal class HomeUi @Inject constructor(
	viewBindingProvider: @JvmSuppressWildcards (View) -> LayoutHomeBinding
) : ViewBindingUi<LayoutHomeBinding>(viewBindingProvider) {
	override fun onBound(viewBinding: LayoutHomeBinding, lifecycleOwner: LifecycleOwner) {
		super.onBound(viewBinding, lifecycleOwner)
		viewBinding.run {
		
		}
	}
}