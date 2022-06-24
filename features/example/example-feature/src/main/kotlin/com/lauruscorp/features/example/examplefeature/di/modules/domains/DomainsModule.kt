package com.lauruscorp.features.example.examplefeature.di.modules.domains

import com.lauruscorp.features.example.examplefeature.di.modules.domains.exampledomain.ExampleDomainModule
import dagger.Module

@Module(
	includes = [
		ExampleDomainModule::class
	]
)
internal interface DomainsModule