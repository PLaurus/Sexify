package com.lauruscorp.sexify_android.features.menu.di.modules.domain

import com.lauruscorp.features.menu_domain.api.MenuDomainApi
import com.lauruscorp.features.menu_domain.api.MenuDomainDependencies
import com.lauruscorp.features.menu_domain.api.MenuDomainFactory
import com.lauruscorp.features.menu_domain.store.MenuStore
import com.lauruscorp.sexify_android.features.menu.di.component.MenuFeatureComponent
import com.lauruscorp.sexify_android.features.menu.di.component.scope.MenuFeatureScope
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal interface DomainModule {
	@Binds
	fun provideMenuDomainDependencies(
		menuFeatureComponent: MenuFeatureComponent
	): MenuDomainDependencies
	
	companion object {
		@Provides
		fun provideMenuDomainFactory(
			dependencies: MenuDomainDependencies
		): MenuDomainFactory {
			return MenuDomainFactory(dependencies)
		}
		
		@Provides
		@MenuFeatureScope
		fun provideMenuDomainApi(
			menuDomainFactory: MenuDomainFactory
		): MenuDomainApi {
			return menuDomainFactory.create()
		}
		
		@Provides
		fun provideMenuStore(
			menuDomainApi: MenuDomainApi
		): MenuStore {
			return menuDomainApi.getStore()
		}
	}
}