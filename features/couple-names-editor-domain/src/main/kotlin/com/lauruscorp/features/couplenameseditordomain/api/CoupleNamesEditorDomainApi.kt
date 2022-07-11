package com.lauruscorp.features.couplenameseditordomain.api

import com.lauruscorp.features.couplenameseditordomain.store.CoupleNamesEditorStore

interface CoupleNamesEditorDomainApi {
	fun getStore(): CoupleNamesEditorStore
}