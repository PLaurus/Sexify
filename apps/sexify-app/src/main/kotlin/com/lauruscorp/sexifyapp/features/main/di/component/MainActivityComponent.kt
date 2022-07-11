package com.lauruscorp.sexifyapp.features.main.di.component

import android.content.Context
import androidx.lifecycle.ViewModelStoreOwner
import com.lauruscorp.core.di.dagger.qualifiers.context.ActivityContextQualifier
import com.lauruscorp.features.maindomain.api.MainDomainDependencies
import com.lauruscorp.sexifyapp.application.di.component.SexifyApplicationComponent
import com.lauruscorp.sexifyapp.features.main.di.component.scope.MainActivityScope
import com.lauruscorp.sexifyapp.features.main.di.modules.domain.DomainModule
import com.lauruscorp.sexifyapp.features.main.di.modules.mappers.MappersModule
import com.lauruscorp.sexifyapp.features.main.di.modules.ui.UiModule
import com.lauruscorp.sexifyapp.features.main.di.modules.viewmodel.ViewModelModule
import com.lauruscorp.sexifyapp.features.main.presentation.MainActivity
import dagger.BindsInstance
import dagger.Subcomponent

@MainActivityScope
@Subcomponent(
	modules = [
		UiModule::class,
		DomainModule::class,
		ViewModelModule::class,
		MappersModule::class
	]
)
internal interface MainActivityComponent : MainDomainDependencies {
	fun inject(activity: MainActivity)
	
	@Subcomponent.Factory
	interface Factory {
		fun create(
			@BindsInstance @ActivityContextQualifier activityContext: Context,
			@BindsInstance viewModelStoreOwner: ViewModelStoreOwner
		): MainActivityComponent
	}
	
	companion object {
		operator fun invoke(
			sexifyApplicationComponent: SexifyApplicationComponent,
			activityContext: Context,
			viewModelStoreOwner: ViewModelStoreOwner
		): MainActivityComponent {
			return sexifyApplicationComponent
				.getMainActivityComponentFactory()
				.create(
					activityContext = activityContext,
					viewModelStoreOwner = viewModelStoreOwner
				)
		}
	}
}