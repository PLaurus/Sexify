package m_package_1.presentation.di.modules.mappers

import _M_PACKAGE_0_.store._M_NAME_PASCAL_Store
import com.lauruscorp.core_jvm.mapping.Mapper
import dagger.Binds
import dagger.Module
import m_package_1.presentation.entities.UiError
import m_package_1.presentation.mappers.LabelToUiErrorMapper

@Module
internal abstract class MappersModule {
    @Binds
    abstract fun provideLabelToUiErrorMapper(
        mapper: LabelToUiErrorMapper
    ): Mapper<_M_NAME_PASCAL_Store.Label, UiError>
}