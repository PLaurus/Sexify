package com.lauruscorp.features.couplenameseditordomain.di.component

import com.lauruscorp.features.couplenameseditordomain.api.CoupleNamesEditorDomainApi
import com.lauruscorp.features.couplenameseditordomain.dependencies.ExternalDependencies
import com.lauruscorp.features.couplenameseditordomain.di.modules.store.CoupleNamesEditorStoreModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
	dependencies = [
		ExternalDependencies::class
	],
	modules = [
		CoupleNamesEditorStoreModule::class
	]
)
internal interface CoupleNamesEditorComponent : CoupleNamesEditorDomainApi {
	@Component.Factory
	interface Factory {
		fun create(
			dependencies: ExternalDependencies
		): CoupleNamesEditorComponent
	}
	
	companion object {
		operator fun invoke(
			dependencies: ExternalDependencies
		): CoupleNamesEditorComponent {
			return DaggerCoupleNamesEditorComponent.factory()
				.create(dependencies)
		}
	}
}