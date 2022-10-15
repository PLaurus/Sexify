package com.lauruscorp.core_android.android.view

import android.view.View
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

@Deprecated(message = "use same function from :core module")
fun View.flowClicks(): Flow<Unit> = callbackFlow {
    setOnClickListener {
        trySend(Unit)
    }

    awaitClose { setOnClickListener(null) }
}