package com.lauruscorp.features.example.exampledomain.api

import com.lauruscorp.features.example.exampledomain.store.ExampleStore

interface ExampleDomainApi {
	fun getStore(): ExampleStore
}