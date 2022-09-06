package m_package_1.di.modules.domains

import dagger.Module
import m_package_1.di.modules.domains._M_NAME_LOWERCASE_domain._M_NAME_PASCAL_DomainModule

@Module(
    includes = [
        _M_NAME_PASCAL_DomainModule::class
    ]
)
internal interface DomainsModule