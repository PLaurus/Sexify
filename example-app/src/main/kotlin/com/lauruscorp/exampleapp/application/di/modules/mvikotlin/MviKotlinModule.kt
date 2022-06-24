package com.lauruscorp.exampleapp.application.di.modules.mvikotlin

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.logging.logger.DefaultLogFormatter
import com.arkivanov.mvikotlin.logging.logger.DefaultLogger
import com.arkivanov.mvikotlin.logging.logger.LogFormatter
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.arkivanov.mvikotlin.timetravel.store.TimeTravelStoreFactory
import com.lauruscorp.exampleapp.BuildConfig
import com.lauruscorp.exampleapp.application.di.component.scope.ExampleApplicationScope
import com.lauruscorp.exampleapp.application.di.modules.mvikotlin.qualifiers.DefaultLogFormatterQualifier
import com.lauruscorp.exampleapp.application.di.modules.mvikotlin.qualifiers.DefaultStoreFactoryQualifier
import com.lauruscorp.exampleapp.application.di.modules.mvikotlin.qualifiers.LoggingStoreFactoryQualifier
import com.lauruscorp.exampleapp.application.di.modules.mvikotlin.qualifiers.TimeTravelStoreFactoryQualifier
import dagger.Module
import dagger.Provides

@Module
internal class MviKotlinModule {
	
	@Provides
	@ExampleApplicationScope
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
	@ExampleApplicationScope
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
	@ExampleApplicationScope
	@TimeTravelStoreFactoryQualifier
	fun provideTimeTravelStoreFactory(): StoreFactory {
		return TimeTravelStoreFactory()
	}
	
	@Provides
	@ExampleApplicationScope
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