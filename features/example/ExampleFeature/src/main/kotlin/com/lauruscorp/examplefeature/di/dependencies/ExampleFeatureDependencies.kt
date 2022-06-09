package com.lauruscorp.examplefeature.di.dependencies

import com.lauruscorp.examplefeature.di.qualifiers.ExampleTextDependencyQualifier

interface ExampleFeatureDependencies {
	@ExampleTextDependencyQualifier
	fun getTestText(): String
}