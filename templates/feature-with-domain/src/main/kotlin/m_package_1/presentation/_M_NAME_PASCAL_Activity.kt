package m_package_1.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lauruscorp.core.android.requireContentView
import com.lauruscorp.core.ui.ViewBindingUi
import m_package_1.R
import m_package_1.api._M_NAME_PASCAL_FeatureLauncher
import m_package_1.databinding.LayoutMNameSnakeBinding
import m_package_1.presentation.di.component._M_NAME_PASCAL_PresentationComponent
import javax.inject.Inject

internal class _M_NAME_PASCAL_Activity : AppCompatActivity(R.layout.layout__m_name_snake_) {
    @Inject
    lateinit var _M_NAME_CAMEL_Ui: ViewBindingUi<LayoutMNameSnakeBinding>

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