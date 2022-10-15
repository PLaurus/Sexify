package com.lauruscorp.core_android.di.dagger.mapkeys

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@Deprecated(message = "use same annotation from :core module")
@MapKey
@MustBeDocumented
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY)
annotation class ViewModelMapKey(val value: KClass<out ViewModel>)