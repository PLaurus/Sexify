package m_package_1.presentation.di.component

import androidx.lifecycle.ViewModelStoreOwner
import dagger.BindsInstance
import dagger.Subcomponent
import m_package_1.di.component._M_NAME_PASCAL_FeatureComponentsRegistry
import m_package_1.presentation._M_NAME_PASCAL_Activity
import m_package_1.presentation.di.component.scope._M_NAME_PASCAL_PresentationScope
import m_package_1.presentation.di.modules.mappers.MappersModule
import m_package_1.presentation.di.modules.ui.UiModule
import m_package_1.presentation.di.modules.viewmodel.ViewModelModule

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