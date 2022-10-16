package com.lauruscorp.exampleapp.activity.ui

import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.lauruscorp.core.ui.ViewBindingUi
import com.lauruscorp.exampleapp.application.navigation.ExampleAppNavigation
import com.lauruscorp.exampleapp.databinding.LayoutExampleAppBinding
import javax.inject.Inject

internal class ExampleAppUi @Inject constructor(
    viewBindingProvider: @JvmSuppressWildcards (View) -> LayoutExampleAppBinding,
    private val navigation: ExampleAppNavigation
) : ViewBindingUi<LayoutExampleAppBinding>(viewBindingProvider) {
    override fun onBound(viewBinding: LayoutExampleAppBinding, lifecycleOwner: LifecycleOwner) {
        super.onBound(viewBinding, lifecycleOwner)
        viewBinding.run {
            initializeStartExampleFeatureButton()
            initializeStartExample2FeatureButton()
        }
    }

    private fun LayoutExampleAppBinding.initializeStartExampleFeatureButton() {
        startExampleFeatureButton.setOnClickListener {
            navigation.launchExampleFeature(it.context)
        }
    }

    private fun LayoutExampleAppBinding.initializeStartExample2FeatureButton() {
        startExample2FeatureButton.setOnClickListener {
            navigation.launchExample2Feature(it.context)
        }
    }
}