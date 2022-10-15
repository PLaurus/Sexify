package com.lauruscorp.core_android.android

import android.app.Activity
import android.view.View
import android.view.ViewGroup

@Deprecated(message = "use same property from :core module")
val Activity.contentViewContainer: ViewGroup
    get() = window.decorView.findViewById(android.R.id.content)

@Deprecated(message = "use same property from :core module")
val Activity.contentView: View?
    get() = contentViewContainer.getChildAt(0)

@Deprecated(message = "use same property from :core module")
val Activity.requireContentView: View
    get() = requireNotNull(contentView)