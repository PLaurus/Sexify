package com.lauruscorp.sexify_android.features.couplenameseditor.di.modules.domain

import com.lauruscorp.features.couplenameseditordomain.api.CoupleNamesEditorDomainApi
import com.lauruscorp.features.couplenameseditordomain.api.CoupleNamesEditorDomainDependencies
import com.lauruscorp.features.couplenameseditordomain.api.CoupleNamesEditorDomainFactory
import com.lauruscorp.features.couplenameseditordomain.store.CoupleNamesEditorStore
import com.lauruscorp.sexify_android.features.couplenameseditor.di.component.CoupleNamesEditorFeatureComponent
import com.lauruscorp.sexify_android.features.main.di.component.scope.MainActivityScope
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal interface DomainModule {
	@Binds
	fun provideCoupleNamesEditorDomainDependencies(
		coupleNamesEditorFeatureComponent: CoupleNamesEditorFeatureComponent
	): CoupleNamesEditorDomainDependencies
	
	companion object {
		@Provides
		fun provideCoupleNamesEditorDomainFactory(
			dependencies: CoupleNamesEditorDomainDependencies
		): CoupleNamesEditorDomainFactory {
			return CoupleNamesEditorDomainFactory(dependencies)
		}
		
		@Provides
		@MainActivityScope
		fun provideCoupleNamesEditorDomainApi(
			coupleNamesEditorDomainFactory: CoupleNamesEditorDomainFactory
		): CoupleNamesEditorDomainApi {
			return coupleNamesEditorDomainFactory.create()
		}
		
		@Provides
		fun provideCoupleNamesEditorStore(
			coupleNamesEditorDomainApi: CoupleNamesEditorDomainApi
		): CoupleNamesEditorStore {
			return coupleNamesEditorDomainApi.getStore()
		}
	}
}