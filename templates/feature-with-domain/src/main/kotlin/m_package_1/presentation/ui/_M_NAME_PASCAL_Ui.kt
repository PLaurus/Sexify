package m_package_1.presentation.ui

import android.view.View
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import com.lauruscorp.core_android.ui.ViewBindingUi
import m_package_1.databinding.LayoutMNameSnakeBinding
import m_package_1.presentation.entities.UiError
import m_package_1.presentation.viewmodel._M_NAME_PASCAL_ViewModel
import javax.inject.Inject

internal class _M_NAME_PASCAL_Ui @Inject constructor(
    viewBindingProvider: @JvmSuppressWildcards (view: View) -> LayoutMNameSnakeBinding,
    private val viewModel: _M_NAME_PASCAL_ViewModel
) : ViewBindingUi<LayoutMNameSnakeBinding>(viewBindingProvider) {

    override fun onBound(
        viewBinding: LayoutMNameSnakeBinding,
        lifecycleOwner: LifecycleOwner
    ) {
        super.onBound(viewBinding, lifecycleOwner)
        viewBinding.run {
            initializeToast(lifecycleOwner)
        }
    }

    private fun LayoutMNameSnakeBinding.initializeToast(lifecycleOwner: LifecycleOwner) {
        viewModel.errorEvent.observe(lifecycleOwner) { uiError ->
            val context = root.context
            val message = when (uiError) {
                is UiError.System -> uiError.exception.localizedMessage
            }

            Toast.makeText(context, message, Toast.LENGTH_SHORT)
                .show()
        }
    }
}