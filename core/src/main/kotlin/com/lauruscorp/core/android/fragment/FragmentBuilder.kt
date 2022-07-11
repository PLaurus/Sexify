package com.lauruscorp.core.android.fragment

import androidx.fragment.app.Fragment
import kotlin.reflect.KClass

abstract class FragmentBuilder<T : Fragment>(
	val fragmentClass: KClass<in T>
) {
	companion object {
		inline operator fun <reified T : Fragment> invoke(
			noinline block: () -> T
		): FragmentBuilder<T> {
			return object : FragmentBuilder<T>(T::class) {
				override fun build(): T {
					return block.invoke()
				}
			}
		}
	}
	
	abstract fun build(): T
}