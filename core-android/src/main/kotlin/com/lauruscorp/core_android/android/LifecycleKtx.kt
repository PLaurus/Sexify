package com.lauruscorp.core_android.android

import androidx.lifecycle.Lifecycle

@Deprecated(message = "use same property from :core module")
val Lifecycle.isAtLeastDestroyed: Boolean
    get() = currentState.isAtLeast(Lifecycle.State.DESTROYED)

@Deprecated(message = "use same property from :core module")
val Lifecycle.isAtLeastInitialized: Boolean
    get() = currentState.isAtLeast(Lifecycle.State.INITIALIZED)

@Deprecated(message = "use same property from :core module")
val Lifecycle.isAtLeastCreated: Boolean
    get() = currentState.isAtLeast(Lifecycle.State.CREATED)

@Deprecated(message = "use same property from :core module")
val Lifecycle.isAtLeastStarted: Boolean
    get() = currentState.isAtLeast(Lifecycle.State.STARTED)

@Deprecated(message = "use same property from :core module")
val Lifecycle.isAtLeastResumed: Boolean
    get() = currentState.isAtLeast(Lifecycle.State.RESUMED)