package application.di.modules.coroutines.qualifiers

import javax.inject.Qualifier

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
internal annotation class MainCoroutineDispatcherQualifier