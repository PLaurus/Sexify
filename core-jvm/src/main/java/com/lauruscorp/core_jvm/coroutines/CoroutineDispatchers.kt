package com.lauruscorp.core_jvm.coroutines

import kotlinx.coroutines.CoroutineDispatcher

@Deprecated(message = "use same interface from :core module")
interface CoroutineDispatchers {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}