package m_package_1.di.component

import _M_PACKAGE_0_.api._M_NAME_PASCAL_DomainDependencies
import android.content.Context
import com.lauruscorp.features.core.di.FeatureComponent
import com.lauruscorp.features.core.di.qualifiers.FeatureIdQualifier
import dagger.BindsInstance
import dagger.Component
import m_package_1.api._M_NAME_PASCAL_FeatureDependencies
import m_package_1.di.component.scope._M_NAME_PASCAL_FeatureScope
import m_package_1.di.modules.domains.DomainsModule
import m_package_1.di.modules.features.FeaturesModule
import m_package_1.di.modules.subcomponents._M_NAME_PASCAL_FeatureSubcomponents
import m_package_1.presentation.di.component._M_NAME_PASCAL_PresentationComponent

@_M_NAME_PASCAL_FeatureScope
@Component(
    dependencies = [
        _M_NAME_PASCAL_FeatureDependencies::class
    ],
    modules = [
        _M_NAME_PASCAL_FeatureSubcomponents::class,
        FeaturesModule::class,
        DomainsModule::class
    ]
)
internal interface _M_NAME_PASCAL_FeatureComponent : FeatureComponent,
    _M_NAME_PASCAL_DomainDependencies {
    fun get_M_NAME_PASCAL_PresentationComponentFactory(): _M_NAME_PASCAL_PresentationComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance @FeatureIdQualifier featureId: Long,
            dependencies: _M_NAME_PASCAL_FeatureDependencies,
            @BindsInstance onButtonClick: ((context: Context) -> Unit)
        ): _M_NAME_PASCAL_FeatureComponent
    }

    companion object {
        operator fun invoke(
            featureId: Long,
            dependencies: _M_NAME_PASCAL_FeatureDependencies,
            onButtonClick: ((context: Context) -> Unit)
        ): _M_NAME_PASCAL_FeatureComponent {
            return Dagger_M_NAME_PASCAL_FeatureComponent.factory()
                .create(featureId, dependencies, onButtonClick)
        }
    }
}