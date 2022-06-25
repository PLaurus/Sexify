package com.lauruscorp.features.example.examplefeature.presentation.di.component

import androidx.lifecycle.ViewModelStoreOwner
import com.lauruscorp.features.example.examplefeature.di.component.ExampleFeatureComponentsRegistry
import com.lauruscorp.features.example.examplefeature.presentation.ExampleActivity
import com.lauruscorp.features.example.examplefeature.presentation.di.component.scope.ExamplePresentationScope
import com.lauruscorp.features.example.examplefeature.presentation.di.modules.ui.UiModule
import com.lauruscorp.features.example.examplefeature.presentation.di.modules.viewmodel.ViewModelModule
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
		operator fun invoke(
			featureId: Long,
			viewModelStoreOwner: ViewModelStoreOwner
		): ExamplePresentationComponent {
			return ExampleFeatureComponentsRegistry[featureId]
				.getExamplePresentationComponentFactory()
				.create(viewModelStoreOwner)
		}
	}
}