package com.lauruscorp.sexify_android.features.main.plugged_in_features

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.lauruscorp.core.android.fragment.FragmentBuilder
import javax.inject.Inject

internal class PluggedInFeatureFragmentFactory @Inject constructor(
    private val fragmentsBuilders: @JvmSuppressWildcards Set<FragmentBuilder<*>>
) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        val clazz = loadFragmentClass(classLoader, className)

        return fragmentsBuilders
            .find { builder -> builder.fragmentClass.java == clazz }
            ?.build()
            ?: super.instantiate(classLoader, className)
    }
}