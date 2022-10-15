package com.lauruscorp.features.couplenameseditordomain.api

import com.lauruscorp.features.couplenameseditordomain.dependencies.ExternalDependencies
import com.lauruscorp.features.couplenameseditordomain.di.component.CoupleNamesEditorComponent

class CoupleNamesEditorDomainFactory(
	private val dependencies: ExternalDependencies
) {
	fun create(): CoupleNamesEditorDomainApi {
		return CoupleNamesEditorComponent(
			dependencies = dependencies
		)
	}
}