package com.lauruscorp.features.categoriesselectiondomain.api

import com.lauruscorp.features.categoriesselectiondomain.store.CategoriesSelectionStore

interface CategoriesSelectionDomainApi {
	fun getStore(): CategoriesSelectionStore
}