package features.task_editor.di.modules.res.strings

import dagger.Module
import dagger.Provides
import features.task_editor.di.component.qualifiers.InvalidInitialDataStringQualifier
import features.task_editor.presentation.res.StringsRes

@Module
internal class StringsModule {
	@Provides
	@InvalidInitialDataStringQualifier
	fun provideInvalidInitialDataString(): String {
		return StringsRes.InvalidInitialData
	}
}