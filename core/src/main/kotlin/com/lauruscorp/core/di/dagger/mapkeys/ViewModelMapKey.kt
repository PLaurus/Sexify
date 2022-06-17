package com.lauruscorp.core.di.dagger.mapkeys

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
@MustBeDocumented
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY)
annotation class ViewModelMapKey(val value: KClass<out ViewModel>)