package com.lauruscorp.core_android.android.livedata

import androidx.lifecycle.MutableLiveData
import kotlin.reflect.KProperty

@Deprecated(message = "use function class from :core module")
operator fun <T> MutableLiveData<T>.setValue(thisObj: Any?, property: KProperty<*>, value: T?) {
	this.value = value
}

@Deprecated(message = "use same function from :core module")
operator fun <T> MutableLiveData<T>.getValue(thisObj: Any?, property: KProperty<*>): T? = this.value