package _M_PACKAGE_1_.presentation.di.component

import _M_PACKAGE_1_.di.component._M_NAME_PASCAL_FeatureComponentsRegistry
import _M_PACKAGE_1_.presentation._M_NAME_PASCAL_Activity
import _M_PACKAGE_1_.presentation.di.component.scope._M_NAME_PASCAL_PresentationScope
import _M_PACKAGE_1_.presentation.di.modules.mappers.MappersModule
import _M_PACKAGE_1_.presentation.di.modules.ui.UiModule
import _M_PACKAGE_1_.presentation.di.modules.viewmodel.ViewModelModule
import androidx.lifecycle.ViewModelStoreOwner
import dagger.BindsInstance
import dagger.Subcomponent

@_M_NAME_PASCAL_PresentationScope
@Subcomponent(
	modules = [
		UiModule::class,
		ViewModelModule::class,
		MappersModule::class
	]
)
internal interface _M_NAME_PASCAL_PresentationComponent {
	fun inject(activity: _M_NAME_PASCAL_Activity)
	
	@Subcomponent.Factory
	interface Factory {
		fun create(
			@BindsInstance viewModelStoreOwner: ViewModelStoreOwner
		): _M_NAME_PASCAL_PresentationComponent
	}
	
	companion object {
		operator fun invoke(
			featureId: Long,
			viewModelStoreOwner: ViewModelStoreOwner
		): _M_NAME_PASCAL_PresentationComponent {
			return _M_NAME_PASCAL_FeatureComponentsRegistry[featureId]
				.get_M_NAME_PASCAL_PresentationComponentFactory()
				.create(viewModelStoreOwner)
		}
	}
}