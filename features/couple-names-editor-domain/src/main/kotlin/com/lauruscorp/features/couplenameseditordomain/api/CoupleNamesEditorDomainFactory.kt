package com.lauruscorp.features.couplenameseditordomain.api

import com.lauruscorp.features.couplenameseditordomain.di.component.CoupleNamesEditorComponent

class CoupleNamesEditorDomainFactory(
	private val dependencies: CoupleNamesEditorDomainDependencies
) {
	fun create(): CoupleNamesEditorDomainApi {
		return CoupleNamesEditorComponent(
			dependencies = dependencies
		)
	}
}