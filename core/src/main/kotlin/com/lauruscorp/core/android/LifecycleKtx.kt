package com.lauruscorp.core.android

import androidx.lifecycle.Lifecycle

val Lifecycle.isAtLeastDestroyed: Boolean
	get() = currentState.isAtLeast(Lifecycle.State.DESTROYED)

val Lifecycle.isAtLeastInitialized: Boolean
	get() = currentState.isAtLeast(Lifecycle.State.INITIALIZED)

val Lifecycle.isAtLeastCreated: Boolean
	get() = currentState.isAtLeast(Lifecycle.State.CREATED)

val Lifecycle.isAtLeastStarted: Boolean
	get() = currentState.isAtLeast(Lifecycle.State.STARTED)

val Lifecycle.isAtLeastResumed: Boolean
	get() = currentState.isAtLeast(Lifecycle.State.RESUMED)