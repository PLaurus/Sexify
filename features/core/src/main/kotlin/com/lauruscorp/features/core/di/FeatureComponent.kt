package com.lauruscorp.features.core.di

import com.lauruscorp.features.core.di.qualifiers.FeatureIdQualifier

interface FeatureComponent {
	@FeatureIdQualifier
	fun getFeatureId(): Long
}