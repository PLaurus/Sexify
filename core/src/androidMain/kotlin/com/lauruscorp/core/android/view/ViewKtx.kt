package com.lauruscorp.core.android.view

import android.view.View
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

fun View.flowClicks(): Flow<Unit> = callbackFlow {
    setOnClickListener {
        trySend(Unit)
    }

    awaitClose { setOnClickListener(null) }
}