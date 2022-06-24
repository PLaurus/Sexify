package com.lauruscorp.core.coroutines

import kotlinx.coroutines.CoroutineDispatcher

class CustomCoroutineDispatchers(
	override val main: CoroutineDispatcher,
	override val io: CoroutineDispatcher,
	override val default: CoroutineDispatcher,
	override val unconfined: CoroutineDispatcher
) : CoroutineDispatchers