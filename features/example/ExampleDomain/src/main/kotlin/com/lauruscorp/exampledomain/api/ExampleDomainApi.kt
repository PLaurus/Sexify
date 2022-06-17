package com.lauruscorp.exampledomain.api

import com.lauruscorp.exampledomain.ExampleStore

interface ExampleDomainApi {
	fun getStore(): ExampleStore
}