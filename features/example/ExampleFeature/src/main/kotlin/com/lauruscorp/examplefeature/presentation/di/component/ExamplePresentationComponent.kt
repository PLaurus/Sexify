package com.lauruscorp.examplefeature.presentation.di.component

import androidx.lifecycle.ViewModelStoreOwner
import com.lauruscorp.examplefeature.di.component.ExampleFeatureComponentsRegistry
import com.lauruscorp.examplefeature.presentation.ExampleActivity
import com.lauruscorp.examplefeature.presentation.di.component.scope.ExamplePresentationScope
import com.lauruscorp.examplefeature.presentation.di.modules.ui.UiModule
import com.lauruscorp.examplefeature.presentation.di.modules.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Subcomponent

@ExamplePresentationScope
@Subcomponent(
	modules = [
		UiModule::class,
		ViewModelModule::class
	]
)
internal interface ExamplePresentationComponent {
	fun inject(activity: ExampleActivity)
	
	@Subcomponent.Factory
	interface Factory {
		fun create(
			@BindsInstance viewModelStoreOwner: ViewModelStoreOwner
		): ExamplePresentationComponent
	}
	
	companion object {
		fun create(
			featureId: Long,
			viewModelStoreOwner: ViewModelStoreOwner
		): ExamplePresentationComponent {
			return ExampleFeatureComponentsRegistry[featureId]
				.getExamplePresentationComponentFactory()
				.create(viewModelStoreOwner)
		}
	}
}