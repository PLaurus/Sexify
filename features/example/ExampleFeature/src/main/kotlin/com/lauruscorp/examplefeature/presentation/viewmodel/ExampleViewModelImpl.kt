package com.lauruscorp.examplefeature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.lauruscorp.exampledomain.ExampleStore
import javax.inject.Inject

internal class ExampleViewModelImpl @Inject constructor(
	private val store: ExampleStore
) : ViewModel(), ExampleViewModel