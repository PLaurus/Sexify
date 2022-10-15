package com.lauruscorp.sexify_android.features.couplenameseditor.di.modules.domain

import com.lauruscorp.features.couplenameseditordomain.api.CoupleNamesEditorDomainApi
import com.lauruscorp.features.couplenameseditordomain.api.CoupleNamesEditorDomainFactory
import com.lauruscorp.features.couplenameseditordomain.dependencies.ExternalDependencies
import com.lauruscorp.features.couplenameseditordomain.store.CoupleNamesEditorStore
import com.lauruscorp.sexify_android.features.couplenameseditor.domain.dependencies.DomainDependenciesImpl
import com.lauruscorp.sexify_android.features.main.di.component.scope.MainActivityScope
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal interface DomainModule {
	@Binds
	fun provideDomainDependencies(
		domainDependencies: DomainDependenciesImpl
	): ExternalDependencies
	
	companion object {
		@Provides
		fun provideCoupleNamesEditorDomainFactory(
			dependencies: ExternalDependencies
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