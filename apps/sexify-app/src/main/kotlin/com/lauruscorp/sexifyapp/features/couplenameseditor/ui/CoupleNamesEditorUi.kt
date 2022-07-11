package com.lauruscorp.sexifyapp.features.couplenameseditor.ui

import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.lauruscorp.core.ui.ViewBindingUi
import com.lauruscorp.sexifyapp.databinding.LayoutCoupleNamesEditorBinding
import javax.inject.Inject

internal class CoupleNamesEditorUi @Inject constructor(
	viewBindingProvider: @JvmSuppressWildcards (View) -> LayoutCoupleNamesEditorBinding
) : ViewBindingUi<LayoutCoupleNamesEditorBinding>(viewBindingProvider) {
	override fun onBound(viewBinding: LayoutCoupleNamesEditorBinding, lifecycleOwner: LifecycleOwner) {
		super.onBound(viewBinding, lifecycleOwner)
		viewBinding.run {
		
		}
	}
}