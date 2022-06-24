package com.lauruscorp.features.example.example2feature.presentation.di.component

import androidx.lifecycle.ViewModelStoreOwner
import com.lauruscorp.features.example.example2feature.di.component.Example2FeatureComponentsRegistry
import com.lauruscorp.features.example.example2feature.presentation.Example2Activity
import com.lauruscorp.features.example.example2feature.presentation.di.modules.mappers.MappersModule
import com.lauruscorp.features.example.example2feature.presentation.di.modules.ui.UiModule
import com.lauruscorp.features.example.example2feature.presentation.di.modules.viewmodel.ViewModelModule
import com.lauruscorp.features.example.example2feature.presentation.di.component.scope.Example2PresentationScope
import dagger.BindsInstance
import dagger.Subcomponent

@Example2PresentationScope
@Subcomponent(
	modules = [
		UiModule::class,
		ViewModelModule::class,
		MappersModule::class
	]
)
internal interface Example2PresentationComponent {
	fun inject(activity: Example2Activity)
	
	@Subcomponent.Factory
	interface Factory {
		fun create(
			@BindsInstance viewModelStoreOwner: ViewModelStoreOwner
		): Example2PresentationComponent
	}
	
	companion object {
		fun create(
			featureId: Long,
			viewModelStoreOwner: ViewModelStoreOwner
		): Example2PresentationComponent {
			return Example2FeatureComponentsRegistry[featureId]
				.getExample2PresentationComponentFactory()
				.create(viewModelStoreOwner)
		}
	}
}