package com.lauruscorp.core_jvm.coroutines

import kotlinx.coroutines.CoroutineDispatcher

class CustomCoroutineDispatchers(
    override val main: CoroutineDispatcher,
    override val io: CoroutineDispatcher,
    override val default: CoroutineDispatcher,
    override val unconfined: CoroutineDispatcher
) : CoroutineDispatchers