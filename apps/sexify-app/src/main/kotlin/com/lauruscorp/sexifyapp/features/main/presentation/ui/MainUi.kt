package com.lauruscorp.sexifyapp.features.main.presentation.ui

import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.lauruscorp.core.ui.ViewBindingUi
import com.lauruscorp.sexifyapp.databinding.LayoutMainBinding
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