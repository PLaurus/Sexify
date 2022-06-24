package com.lauruscorp.features.example.exampleapp.activity.di.component

import androidx.lifecycle.ViewModelStoreOwner
import com.lauruscorp.features.example.exampleapp.activity.ExampleAppActivity
import com.lauruscorp.features.example.exampleapp.activity.di.component.scope.ExampleActivityScope
import com.lauruscorp.features.example.exampleapp.activity.di.modules.UiModule
import com.lauruscorp.features.example.exampleapp.application.di.component.ExampleApplicationComponent
import dagger.BindsInstance
import dagger.Subcomponent

@ExampleActivityScope
@Subcomponent(
	modules = [
		UiModule::class
	]
)
internal interface ExampleActivityComponent {
	fun inject(activity: ExampleAppActivity)
	
	@Subcomponent.Factory
	interface Factory {
		fun create(
			@BindsInstance viewModelStoreOwner: ViewModelStoreOwner
		): ExampleActivityComponent
	}
	
	companion object {
		operator fun invoke(
			exampleApplicationComponent: ExampleApplicationComponent,
			viewModelStoreOwner: ViewModelStoreOwner
		): ExampleActivityComponent {
			return exampleApplicationComponent.getExampleActivityComponentFactory()
				.create(
					viewModelStoreOwner = viewModelStoreOwner
				)
		}
	}
}