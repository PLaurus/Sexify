package com.lauruscorp.core_android.android.fragment

import androidx.fragment.app.Fragment
import kotlin.reflect.KClass

@Deprecated(message = "use same class from :core module")
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