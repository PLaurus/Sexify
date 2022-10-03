package com.lauruscorp.sexify_android.features.menu.api

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.lauruscorp.core_android.ui.ViewBindingUi
import com.lauruscorp.sexify_android.R
import com.lauruscorp.sexify_android.databinding.LayoutMenuBinding
import com.lauruscorp.sexify_android.features.menu.di.component.MenuFeatureComponent
import com.lauruscorp.sexify_android.features.menu.viewmodel.MenuViewModel
import javax.inject.Inject

class MenuFragment(
    private val dependencies: MenuFeatureDependencies
) : Fragment(R.layout.layout_menu) {
    @Inject
    internal lateinit var ui: ViewBindingUi<LayoutMenuBinding>
    
    @Inject
    internal lateinit var viewModel: MenuViewModel
    
    override fun onAttach(context: Context) {
        super.onAttach(context)
        injectDependencies()
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        manageNavigation()
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ui.bindToViewLifecycleOwner(
            view = view,
            lifecycle = viewLifecycleOwner.lifecycle
        )
    }
    
    private fun injectDependencies() {
        MenuFeatureComponent(
            dependencies = dependencies,
            viewModelStoreOwner = this
        ).inject(this)
    }
    
    private fun manageNavigation() {
        val fragmentManager = requireActivity().supportFragmentManager
        val navHostFragment = fragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val navigationController = navHostFragment.navController
        
        viewModel.startGameClickedEvent.observe(this) {
            navigationController.navigate(
                directions = MenuFragmentDirections.actionMenuToCoupleNamesEditor()
            )
        }
    }
}