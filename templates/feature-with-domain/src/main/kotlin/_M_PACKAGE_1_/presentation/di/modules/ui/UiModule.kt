package _M_PACKAGE_1_.presentation.di.modules.ui

import _M_PACKAGE_1_.databinding.LayoutMNameSnakeBinding
import _M_PACKAGE_1_.presentation.ui._M_NAME_PASCAL_Ui
import android.view.View
import com.lauruscorp.core.ui.ViewBindingUi
import dagger.Binds
import dagger.Module
import dagger.Provides

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