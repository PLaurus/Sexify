package com.lauruscorp.sexifyapp.features.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentFactory
import com.lauruscorp.core_android.android.requireContentView
import com.lauruscorp.core_android.ui.ViewBindingUi
import com.lauruscorp.sexifyapp.R
import com.lauruscorp.sexifyapp.application.SexifyApplication
import com.lauruscorp.sexifyapp.databinding.LayoutMainBinding
import com.lauruscorp.sexifyapp.features.main.di.component.MainActivityComponent
import javax.inject.Inject

internal class MainActivity : AppCompatActivity(R.layout.layout_main) {
    @Inject
    lateinit var fragmentFactory: FragmentFactory

    @Inject
    lateinit var ui: ViewBindingUi<LayoutMainBinding>

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        supportFragmentManager.fragmentFactory = fragmentFactory

        super.onCreate(savedInstanceState)

        ui.bindToViewLifecycleOwner(
            view = requireContentView,
            lifecycle = lifecycle
        )
    }

    private fun injectDependencies() {
        MainActivityComponent(
            sexifyApplicationComponent = (application as SexifyApplication).sexifyApplicationComponent,
            activityContext = this,
            viewModelStoreOwner = this
        ).inject(this)
    }
}