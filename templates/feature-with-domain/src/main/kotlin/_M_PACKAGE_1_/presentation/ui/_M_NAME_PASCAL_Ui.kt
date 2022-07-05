package _M_PACKAGE_1_.presentation.ui

import _M_PACKAGE_1_.databinding.LayoutMNameSnakeBinding
import _M_PACKAGE_1_.presentation.entities.UiError
import _M_PACKAGE_1_.presentation.viewmodel._M_NAME_PASCAL_ViewModel
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import com.lauruscorp.core.ui.ViewBindingUi
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
				is UiError.System -> uiError.throwable.localizedMessage
			}
			
			Toast.makeText(context, message, Toast.LENGTH_SHORT)
				.show()
		}
	}
}