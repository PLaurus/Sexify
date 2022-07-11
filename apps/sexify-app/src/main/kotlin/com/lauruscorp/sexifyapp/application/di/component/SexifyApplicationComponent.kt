package com.lauruscorp.sexifyapp.application.di.component

import android.app.Application
import com.lauruscorp.sexifyapp.application.SexifyApplication
import com.lauruscorp.sexifyapp.application.di.component.scope.SexifyApplicationScope
import com.lauruscorp.sexifyapp.application.di.modules.application.ApplicationModule
import com.lauruscorp.sexifyapp.application.di.modules.coroutines.CoroutinesModule
import com.lauruscorp.sexifyapp.application.di.modules.mvikotlin.MviKotlinModule
import com.lauruscorp.sexifyapp.application.di.modules.subcomponents.SubcomponentsModule
import com.lauruscorp.sexifyapp.application.di.modules.viewmodel.ViewModelModule
import com.lauruscorp.sexifyapp.features.main.di.component.MainActivityComponent
import dagger.BindsInstance
import dagger.Component

@SexifyApplicationScope
@Component(
	modules = [
		ApplicationModule::class,
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