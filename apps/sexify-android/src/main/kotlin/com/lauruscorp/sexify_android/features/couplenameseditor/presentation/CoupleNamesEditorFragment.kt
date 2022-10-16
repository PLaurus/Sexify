package com.lauruscorp.sexify_android.features.couplenameseditor.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.lauruscorp.core.ui.ViewBindingUi
import com.lauruscorp.sexify_android.R
import com.lauruscorp.sexify_android.databinding.LayoutCoupleNamesEditorBinding
import com.lauruscorp.sexify_android.features.couplenameseditor.dependencies.CoupleNamesEditorFeatureDependencies
import com.lauruscorp.sexify_android.features.couplenameseditor.di.component.CoupleNamesEditorFeatureComponent
import javax.inject.Inject

class CoupleNamesEditorFragment(
    private val dependencies: CoupleNamesEditorFeatureDependencies
) : Fragment(R.layout.layout_couple_names_editor) {
    @Inject
    lateinit var ui: ViewBindingUi<LayoutCoupleNamesEditorBinding>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injectDependencies()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ui.bindToViewLifecycleOwner(
            view = view,
            lifecycle = viewLifecycleOwner.lifecycle
        )
    }

    private fun injectDependencies() {
        CoupleNamesEditorFeatureComponent(
            dependencies = dependencies,
            viewModelStoreOwner = this
        ).inject(this)
    }
}