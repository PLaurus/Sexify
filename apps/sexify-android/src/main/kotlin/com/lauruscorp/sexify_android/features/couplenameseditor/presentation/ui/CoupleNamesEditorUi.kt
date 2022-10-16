package com.lauruscorp.sexify_android.features.couplenameseditor.presentation.ui

import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.lauruscorp.core.ui.ViewBindingUi
import com.lauruscorp.sexify_android.databinding.LayoutCoupleNamesEditorBinding
import javax.inject.Inject

internal class CoupleNamesEditorUi @Inject constructor(
    viewBindingProvider: @JvmSuppressWildcards (View) -> LayoutCoupleNamesEditorBinding
) : ViewBindingUi<LayoutCoupleNamesEditorBinding>(viewBindingProvider) {
    override fun onBound(
        viewBinding: LayoutCoupleNamesEditorBinding,
        lifecycleOwner: LifecycleOwner
    ) {
        super.onBound(viewBinding, lifecycleOwner)
        viewBinding.run {

        }
    }
}