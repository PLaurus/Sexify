package com.lauruscorp.features.homedomain.api

import com.lauruscorp.features.homedomain.store.HomeStore

interface HomeDomainApi {
	fun getStore(): HomeStore
}