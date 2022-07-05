package com.lauruscorp.sexifyapp.di.modules.mvikotlin

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.logging.logger.DefaultLogFormatter
import com.arkivanov.mvikotlin.logging.logger.DefaultLogger
import com.arkivanov.mvikotlin.logging.logger.LogFormatter
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.arkivanov.mvikotlin.timetravel.store.TimeTravelStoreFactory
import com.lauruscorp.sexifyapp.BuildConfig
import com.lauruscorp.sexifyapp.di.modules.mvikotlin.qualifiers.DefaultLogFormatterQualifier
import com.lauruscorp.sexifyapp.di.modules.mvikotlin.qualifiers.DefaultStoreFactoryQualifier
import com.lauruscorp.sexifyapp.di.modules.mvikotlin.qualifiers.LoggingStoreFactoryQualifier
import com.lauruscorp.sexifyapp.di.modules.mvikotlin.qualifiers.TimeTravelStoreFactoryQualifier
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class MviKotlinModule {
	
	@Provides
	@Singleton
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
	@Singleton
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
	@Singleton
	@TimeTravelStoreFactoryQualifier
	fun provideTimeTravelStoreFactory(): StoreFactory {
		return TimeTravelStoreFactory()
	}
	
	@Provides
	@Singleton
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