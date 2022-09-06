package m_package_1.di.modules.domains._M_NAME_LOWERCASE_domain

import _M_PACKAGE_0_.api._M_NAME_PASCAL_DomainApi
import _M_PACKAGE_0_.api._M_NAME_PASCAL_DomainDependencies
import _M_PACKAGE_0_.api._M_NAME_PASCAL_DomainFactory
import _M_PACKAGE_0_.store._M_NAME_PASCAL_Store
import dagger.Binds
import dagger.Module
import dagger.Provides
import m_package_1.di.component._M_NAME_PASCAL_FeatureComponent
import m_package_1.di.component.scope._M_NAME_PASCAL_FeatureScope

@Module
internal interface _M_NAME_PASCAL_DomainModule {
    @Binds
    fun provideExampleDomainDependencies(
        example2FeatureComponent: _M_NAME_PASCAL_FeatureComponent
    ): _M_NAME_PASCAL_DomainDependencies

    companion object {
        @Provides
        fun provideExampleDomainFactory(
            dependencies: _M_NAME_PASCAL_DomainDependencies
        ): _M_NAME_PASCAL_DomainFactory {
            return _M_NAME_PASCAL_DomainFactory(dependencies)
        }

        @Provides
        @_M_NAME_PASCAL_FeatureScope
        fun provideExampleDomainApi(
            exampleDomainFactory: _M_NAME_PASCAL_DomainFactory
        ): _M_NAME_PASCAL_DomainApi {
            return exampleDomainFactory.create()
        }

        @Provides
        fun provideExampleStore(
            exampleDomainApi: _M_NAME_PASCAL_DomainApi
        ): _M_NAME_PASCAL_Store {
            return exampleDomainApi.getStore()
        }
    }
}