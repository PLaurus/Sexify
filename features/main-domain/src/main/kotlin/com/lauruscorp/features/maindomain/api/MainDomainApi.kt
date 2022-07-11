package com.lauruscorp.features.maindomain.api

import com.lauruscorp.features.maindomain.store.MainStore

interface MainDomainApi {
	fun getStore(): MainStore
}