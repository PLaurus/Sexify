package com.lauruscorp.features.menu_domain.api

import com.lauruscorp.features.menu_domain.store.MenuStore

interface MenuDomainApi {
	fun getStore(): MenuStore
}