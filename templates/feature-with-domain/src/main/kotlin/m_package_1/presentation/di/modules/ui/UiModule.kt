package m_package_1.presentation.di.modules.ui

import android.view.View
import com.lauruscorp.core_android.ui.ViewBindingUi
import dagger.Binds
import dagger.Module
import dagger.Provides
import m_package_1.databinding.LayoutMNameSnakeBinding
import m_package_1.presentation.ui._M_NAME_PASCAL_Ui

@Module
internal abstract class UiModule {
    @Binds
    abstract fun provide_M_NAME_PASCAL_Ui(
        ui: _M_NAME_PASCAL_Ui
    ): ViewBindingUi<LayoutMNameSnakeBinding>

    companion object {
        @Provides
        fun provideViewBindingProvider(): (view: View) -> LayoutMNameSnakeBinding {
            return LayoutMNameSnakeBinding::bind
        }
    }
}