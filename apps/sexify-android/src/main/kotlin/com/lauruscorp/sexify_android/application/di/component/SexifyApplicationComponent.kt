package com.lauruscorp.sexify_android.application.di.component

import android.app.Application
import com.lauruscorp.sexify_android.application.SexifyApplication
import com.lauruscorp.sexify_android.application.di.component.scope.SexifyApplicationScope
import com.lauruscorp.sexify_android.application.di.modules.application.ApplicationModule
import com.lauruscorp.sexify_android.application.di.modules.coroutines.CoroutinesModule
import com.lauruscorp.sexify_android.application.di.modules.data_sources.DataSourcesModule
import com.lauruscorp.sexify_android.application.di.modules.databases.DatabasesModule
import com.lauruscorp.sexify_android.application.di.modules.initialization.InitializationModule
import com.lauruscorp.sexify_android.application.di.modules.mvikotlin.MviKotlinModule
import com.lauruscorp.sexify_android.application.di.modules.repositories.RepositoriesModule
import com.lauruscorp.sexify_android.application.di.modules.subcomponents.SubcomponentsModule
import com.lauruscorp.sexify_android.application.di.modules.viewmodel.ViewModelModule
import com.lauruscorp.sexify_android.features.main.di.component.MainActivityComponent
import dagger.BindsInstance
import dagger.Component

@SexifyApplicationScope
@Component(
	modules = [
		InitializationModule::class,
		ApplicationModule::class,
		DatabasesModule::class,
		DataSourcesModule::class,
		RepositoriesModule::class,
		SubcomponentsModule::class,
		CoroutinesModule::class,
		MviKotlinModule::class,
		ViewModelModule::class
	]
)
internal interface SexifyApplicationComponent {
	fun inject(application: SexifyApplication)
	fun getMainActivityComponentFactory(): MainActivityComponent.Factory
	
	@Component.Factory
	interface Factory {
		fun create(
			@BindsInstance application: Application
		): SexifyApplicationComponent
	}
	
	companion object {
		operator fun invoke(
			application: Application
		): SexifyApplicationComponent {
			return DaggerSexifyApplicationComponent.factory()
				.create(
					application = application
				)
		}
	}
}