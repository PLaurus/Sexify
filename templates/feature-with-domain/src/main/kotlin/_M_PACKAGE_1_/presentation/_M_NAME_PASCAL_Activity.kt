package _M_PACKAGE_1_.presentation

import _M_PACKAGE_1_.R
import _M_PACKAGE_1_.api._M_NAME_PASCAL_FeatureLauncher
import _M_PACKAGE_1_.databinding.LayoutMNameSnakeBinding
import _M_PACKAGE_1_.presentation.di.component._M_NAME_PASCAL_PresentationComponent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lauruscorp.core.android.requireContentView
import com.lauruscorp.core.ui.ViewBindingUi
import javax.inject.Inject

internal class _M_NAME_PASCAL_Activity : AppCompatActivity(R.layout.layout__m_name_snake_) {
	@Inject lateinit var _M_NAME_CAMEL_Ui: ViewBindingUi<LayoutMNameSnakeBinding>
	
	override fun onCreate(savedInstanceState: Bundle?) {
		injectDependencies()
		super.onCreate(savedInstanceState)
		_M_NAME_CAMEL_Ui.bindToViewLifecycleOwner(
			view = requireContentView,
			lifecycle = lifecycle
		)
	}
	
	private fun injectDependencies() {
		_M_NAME_PASCAL_PresentationComponent(
			featureId = intent.getLongExtra(_M_NAME_PASCAL_FeatureLauncher.FEATURE_ID_KEY, 0),
			viewModelStoreOwner = this
		).inject(this)
	}
}