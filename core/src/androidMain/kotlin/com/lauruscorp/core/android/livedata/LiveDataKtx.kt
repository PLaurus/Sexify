package com.lauruscorp.core.android.livedata

import androidx.lifecycle.MutableLiveData
import kotlin.reflect.KProperty

operator fun <T> MutableLiveData<T>.setValue(thisObj: Any?, property: KProperty<*>, value: T?) {
    this.value = value
}

operator fun <T> MutableLiveData<T>.getValue(thisObj: Any?, property: KProperty<*>): T? = this.value