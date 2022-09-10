package application.di.modules.mvikotlin

import application.BuildConfig
import application.di.component.scope.ApplicationScope
import application.di.modules.mvikotlin.qualifiers.DefaultLogFormatterQualifier
import application.di.modules.mvikotlin.qualifiers.DefaultStoreFactoryQualifier
import application.di.modules.mvikotlin.qualifiers.LoggingStoreFactoryQualifier
import application.di.modules.mvikotlin.qualifiers.TimeTravelStoreFactoryQualifier
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.logging.logger.DefaultLogFormatter
import com.arkivanov.mvikotlin.logging.logger.DefaultLogger
import com.arkivanov.mvikotlin.logging.logger.LogFormatter
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.arkivanov.mvikotlin.timetravel.store.TimeTravelStoreFactory
import dagger.Module
import dagger.Provides

@Module
internal class MviKotlinModule {
    @Provides
    @ApplicationScope
    fun provideStoreFactory(
        @LoggingStoreFactoryQualifier loggingStoreFactory: StoreFactory,
        @DefaultStoreFactoryQualifier defaultStoreFactory: StoreFactory
    ): StoreFactory {
        return when (BuildConfig.DEBUG) {
            true -> loggingStoreFactory
            else -> defaultStoreFactory
        }
    }

    @Provides
    @ApplicationScope
    @LoggingStoreFactoryQualifier
    fun provideLoggingStoreFactory(
        @TimeTravelStoreFactoryQualifier timeTravelStoreFactory: StoreFactory,
        @DefaultLogFormatterQualifier defaultLogFormatter: LogFormatter
    ): StoreFactory {
        return LoggingStoreFactory(
            delegate = timeTravelStoreFactory,
            logger = DefaultLogger,
            logFormatter = defaultLogFormatter
        )
    }

    @Provides
    @ApplicationScope
    @TimeTravelStoreFactoryQualifier
    fun provideTimeTravelStoreFactory(): StoreFactory {
        return TimeTravelStoreFactory()
    }

    @Provides
    @ApplicationScope
    @DefaultStoreFactoryQualifier
    fun provideDefaultStoreFactory(): StoreFactory {
        return DefaultStoreFactory()
    }

    @Provides
    @DefaultLogFormatterQualifier
    fun provideDefaultLogFormatter(): LogFormatter {
        return DefaultLogFormatter()
    }
}