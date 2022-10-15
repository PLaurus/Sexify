package com.lauruscorp.core_jvm.coroutines

import kotlinx.coroutines.CoroutineDispatcher

@Deprecated(message = "use same class from :core module")
class CustomCoroutineDispatchers(
    override val main: CoroutineDispatcher,
    override val io: CoroutineDispatcher,
    override val default: CoroutineDispatcher,
    override val unconfined: CoroutineDispatcher
) : CoroutineDispatchers