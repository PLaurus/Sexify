package _M_PACKAGE_1_.presentation.di.modules.mappers

import _M_PACKAGE_0_.store._M_NAME_PASCAL_Store
import _M_PACKAGE_1_.presentation.entities.UiError
import _M_PACKAGE_1_.presentation.mappers.LabelToUiErrorMapper
import com.lauruscorp.core.mapping.Mapper
import dagger.Binds
import dagger.Module

@Module
internal abstract class MappersModule {
	@Binds
	abstract fun provideLabelToUiErrorMapper(
		mapper: LabelToUiErrorMapper
	): @JvmSuppressWildcards Mapper<_M_NAME_PASCAL_Store.Label, UiError>
}