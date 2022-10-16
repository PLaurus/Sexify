package com.lauruscorp.features.example.examplefeature.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lauruscorp.core.android.requireContentView
import com.lauruscorp.core.ui.ViewBindingUi
import com.lauruscorp.features.example.examplefeature.R
import com.lauruscorp.features.example.examplefeature.databinding.LayoutExampleBinding
import com.lauruscorp.features.example.examplefeature.presentation.di.component.ExamplePresentationComponent
import javax.inject.Inject

internal class ExampleActivity : AppCompatActivity(R.layout.layout_example) {
    @Inject
    lateinit var ui: ViewBindingUi<LayoutExampleBinding>

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        ui.bindToViewLifecycleOwner(
            view = requireContentView,
            lifecycle = lifecycle
        )
    }

    private fun injectDependencies() {
        ExamplePresentationComponent(
            featureId = intent.getLongExtra(FEATURE_ID_KEY, 0),
            viewModelStoreOwner = this
        ).inject(this)
    }

    companion object {
        const val FEATURE_ID_KEY = "FEATURE_ID_KEY"
    }
}