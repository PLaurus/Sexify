package com.lauruscorp.sexify_android.features.loading.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.lauruscorp.sexify_android.R
import com.lauruscorp.sexify_android.features.loading.dependencies.LoadingFeatureDependencies
import com.lauruscorp.sexify_android.features.loading.di.component.LoadingFeatureComponent
import com.lauruscorp.sexify_android.features.loading.presentation.ui.LoadingFeatureScreen
import com.lauruscorp.sexify_android.features.loading.presentation.viewmodel.LoadingViewModel
import com.lauruscorp.sexify_domain.loading.entities.LoadingState
import javax.inject.Inject

class LoadingFragment(
    private val dependencies: LoadingFeatureDependencies
) : Fragment() {

    @Inject
    internal lateinit var viewModel: LoadingViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injectDependencies()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        manageNavigation()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val viewModel = remember { viewModel }
                LoadingFeatureScreen(loadingState = viewModel.loadingState.observeAsState().value!!)
            }
        }
    }

    private fun injectDependencies() {
        LoadingFeatureComponent(
            dependencies = dependencies,
            viewModelStoreOwner = this
        ).inject(this)
    }

    private fun manageNavigation() {
        val fragmentManager = requireActivity().supportFragmentManager
        val navHostFragment =
            fragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val navigationController = navHostFragment.navController

        viewModel.loadingState.observe(this) { loadingState ->
            if (loadingState == LoadingState.Loaded) {
                navigationController.navigate(
                    directions = LoadingFragmentDirections.actionLoadingToMenu()
                )
            }
        }
    }
}