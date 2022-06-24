package com.lauruscorp.exampledomain.api

import com.lauruscorp.exampledomain.store.ExampleStore

interface ExampleDomainApi {
	fun getStore(): ExampleStore
}