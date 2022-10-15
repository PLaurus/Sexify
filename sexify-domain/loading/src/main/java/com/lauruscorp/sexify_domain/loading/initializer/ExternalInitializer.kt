package com.lauruscorp.sexify_domain.loading.initializer

interface ExternalInitializer {
    suspend fun initialize()
}