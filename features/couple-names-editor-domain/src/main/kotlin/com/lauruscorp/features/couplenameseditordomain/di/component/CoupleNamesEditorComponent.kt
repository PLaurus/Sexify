package com.lauruscorp.features.couplenameseditordomain.di.component

import com.lauruscorp.features.couplenameseditordomain.api.CoupleNamesEditorDomainApi
import com.lauruscorp.features.couplenameseditordomain.api.CoupleNamesEditorDomainDependencies
import com.lauruscorp.features.couplenameseditordomain.di.modules.store.CoupleNamesEditorStoreModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
	dependencies = [
		CoupleNamesEditorDomainDependencies::class
	],
	modules = [
		CoupleNamesEditorStoreModule::class
	]
)
internal interface CoupleNamesEditorComponent : CoupleNamesEditorDomainApi {
	@Component.Factory
	interface Factory {
		fun create(
			dependencies: CoupleNamesEditorDomainDependencies
		): CoupleNamesEditorComponent
	}
	
	companion object {
		operator fun invoke(
			dependencies: CoupleNamesEditorDomainDependencies
		): CoupleNamesEditorComponent {
			return DaggerCoupleNamesEditorComponent.factory()
				.create(dependencies)
		}
	}
}