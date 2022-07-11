package com.lauruscorp.sexifyapp.features.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.lauruscorp.core.android.fragment.FragmentBuilder
import javax.inject.Inject

internal class MainChildFragmentsFactory @Inject constructor(
	private val childFragmentsBuilders: @JvmSuppressWildcards Set<FragmentBuilder<*>>
) : FragmentFactory() {
	override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
		val clazz = loadFragmentClass(classLoader, className)
		
		return childFragmentsBuilders
			.find { builder -> builder.fragmentClass.java == clazz }
			?.build()
			?: super.instantiate(classLoader, className)
	}
}