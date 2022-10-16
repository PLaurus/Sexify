package com.lauruscorp.sexify_android.features.menu.ui

import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.lauruscorp.core.android.view.flowClicks
import com.lauruscorp.core.ui.ViewBindingUi
import com.lauruscorp.sexify_android.databinding.LayoutMenuBinding
import com.lauruscorp.sexify_android.features.menu.viewmodel.MenuViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.sample
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

internal class MenuUi @Inject constructor(
    viewBindingProvider: @JvmSuppressWildcards (View) -> LayoutMenuBinding,
    private val viewModel: MenuViewModel
) : ViewBindingUi<LayoutMenuBinding>(viewBindingProvider) {
    override fun onBound(viewBinding: LayoutMenuBinding, lifecycleOwner: LifecycleOwner) {
        super.onBound(viewBinding, lifecycleOwner)
        viewBinding.run {
            initializeStartButton()
        }
    }
    
    @OptIn(FlowPreview::class)
    private fun LayoutMenuBinding.initializeStartButton() {
        scope?.launch {
            startButton.flowClicks()
                .sample(1000.milliseconds)
                .collect {
                    viewModel.onStartGameClicked()
                }
        }
    }
}