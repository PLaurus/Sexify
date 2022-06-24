package com.lauruscorp.features.example.example2feature.presentation.mappers

import com.lauruscorp.core.mapping.Mapper
import com.lauruscorp.exampledomain.entities.Operation
import com.lauruscorp.features.example.example2feature.presentation.entities.PresentationOperation
import javax.inject.Inject

internal class PresentationOperationToOperationMapper @Inject constructor(
) : Mapper<PresentationOperation, Operation> {
	override fun map(from: PresentationOperation): Operation {
		return when (from) {
			PresentationOperation.None -> Operation.None
			PresentationOperation.Plus -> Operation.Sum
			PresentationOperation.Minus -> Operation.Subtraction
			PresentationOperation.Multiply -> Operation.Multiplication
			PresentationOperation.Divide -> Operation.Division
		}
	}
}