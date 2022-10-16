package application.di.modules.coroutines

import application.di.modules.coroutines.qualifiers.DefaultCoroutineDispatcherQualifier
import application.di.modules.coroutines.qualifiers.IoCoroutineDispatcherQualifier
import application.di.modules.coroutines.qualifiers.MainCoroutineDispatcherQualifier
import application.di.modules.coroutines.qualifiers.UnconfinedCoroutineDispatcherQualifier
import com.lauruscorp.core.coroutines.CoroutineDispatchers
import com.lauruscorp.core.coroutines.CustomCoroutineDispatchers
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
internal class CoroutinesModule {
    @Provides
    fun provideCoroutineDispatchers(
        @MainCoroutineDispatcherQualifier mainCoroutineDispatcher: CoroutineDispatcher,
        @IoCoroutineDispatcherQualifier ioCoroutineDispatcher: CoroutineDispatcher,
        @DefaultCoroutineDispatcherQualifier defaultCoroutineDispatcher: CoroutineDispatcher,
        @UnconfinedCoroutineDispatcherQualifier unconfinedCoroutineDispatcher: CoroutineDispatcher
    ): CoroutineDispatchers {
        return CustomCoroutineDispatchers(
            main = mainCoroutineDispatcher,
            io = ioCoroutineDispatcher,
            default = defaultCoroutineDispatcher,
            unconfined = unconfinedCoroutineDispatcher
        )
    }

    @Provides
    @MainCoroutineDispatcherQualifier
    fun provideMainCoroutineDispatcher(): CoroutineDispatcher {
        // TODO: Try to change to Dispatchers.Main if it is allowed
        return Dispatchers.Default
    }

    @Provides
    @IoCoroutineDispatcherQualifier
    fun provideIoCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    @DefaultCoroutineDispatcherQualifier
    fun provideDefaultCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.Default
    }

    @Provides
    @UnconfinedCoroutineDispatcherQualifier
    fun provideUnconfinedCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.Unconfined
    }
}