package com.lauruscorp.sexify_android.application.initialization

// TODO: implement really working and solid initialization system
interface Initializer {
    val dependencies: List<Class<out Initializer>>
    suspend fun initialize()
}