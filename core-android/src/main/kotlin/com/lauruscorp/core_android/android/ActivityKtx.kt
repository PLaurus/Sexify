package com.lauruscorp.core_android.android

import android.app.Activity
import android.view.View
import android.view.ViewGroup

val Activity.contentViewContainer: ViewGroup
    get() = window.decorView.findViewById(android.R.id.content)

val Activity.contentView: View?
    get() = contentViewContainer.getChildAt(0)

val Activity.requireContentView: View
    get() = requireNotNull(contentView)