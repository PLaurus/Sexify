package com.lauruscorp.features.example.examplefeature.presentation.ui

import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.lauruscorp.core.ui.ViewBindingUi
import com.lauruscorp.features.example.examplefeature.databinding.LayoutExampleBinding
import com.lauruscorp.features.example.examplefeature.presentation.entities.TestObject
import javax.inject.Inject

internal class ExampleUi @Inject constructor(
    viewBindingProvider: @JvmSuppressWildcards (View) -> LayoutExampleBinding
) : ViewBindingUi<LayoutExampleBinding>(viewBindingProvider) {
    override fun onBound(viewBinding: LayoutExampleBinding, lifecycleOwner: LifecycleOwner) {
        super.onBound(viewBinding, lifecycleOwner)
        viewBinding.run {
            initializeTestGcButton()
        }
    }

    private fun LayoutExampleBinding.initializeTestGcButton() {
        testGcButton.setOnClickListener {
            repeat(1_000_000) {
                TestObject(1000000000)
            }
        }
    }
}