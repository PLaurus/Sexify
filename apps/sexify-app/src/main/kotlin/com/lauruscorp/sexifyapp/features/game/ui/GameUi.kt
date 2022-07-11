package com.lauruscorp.sexifyapp.features.game.ui

import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.lauruscorp.core.ui.ViewBindingUi
import com.lauruscorp.sexifyapp.databinding.LayoutGameBinding
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