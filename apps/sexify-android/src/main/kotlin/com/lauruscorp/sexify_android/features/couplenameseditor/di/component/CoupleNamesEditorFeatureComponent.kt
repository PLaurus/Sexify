package com.lauruscorp.sexify_android.features.couplenameseditor.di.component

import androidx.lifecycle.ViewModelStoreOwner
import com.lauruscorp.features.couplenameseditordomain.api.CoupleNamesEditorDomainDependencies
import com.lauruscorp.sexify_android.features.couplenameseditor.api.CoupleNamesEditorFeatureDependencies
import com.lauruscorp.sexify_android.features.couplenameseditor.api.CoupleNamesEditorFragment
import com.lauruscorp.sexify_android.features.couplenameseditor.di.component.scope.CoupleNamesEditorFeatureScope
import com.lauruscorp.sexify_android.features.couplenameseditor.di.modules.domain.DomainModule
import com.lauruscorp.sexify_android.features.couplenameseditor.di.modules.mappers.MappersModule
import com.lauruscorp.sexify_android.features.couplenameseditor.di.modules.ui.UiModule
import com.lauruscorp.sexify_android.features.couplenameseditor.di.modules.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component

@CoupleNamesEditorFeatureScope
@Component(
	dependencies = [
		CoupleNamesEditorFeatureDependencies::class
	],
	modules = [
		UiModule::class,
		ViewModelModule::class,
		MappersModule::class,
		DomainModule::class
	]
)
internal interface CoupleNamesEditorFeatureComponent : CoupleNamesEditorDomainDependencies {
	fun inject(fragment: CoupleNamesEditorFragment)
	
	@Component.Factory
	interface Factory {
		fun create(
			dependencies: CoupleNamesEditorFeatureDependencies,
			@BindsInstance viewModelStoreOwner: ViewModelStoreOwner
		): CoupleNamesEditorFeatureComponent
	}
	
	companion object {
		operator fun invoke(
			dependencies: CoupleNamesEditorFeatureDependencies,
			viewModelStoreOwner: ViewModelStoreOwner
		): CoupleNamesEditorFeatureComponent {
			return DaggerCoupleNamesEditorFeatureComponent.factory()
				.create(
					dependencies = dependencies,
					viewModelStoreOwner = viewModelStoreOwner
				)
		}
	}
}