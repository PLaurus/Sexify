package com.lauruscorp.features.example.example2feature.presentation.mappers

import com.lauruscorp.core.mapping.Mapper
import com.lauruscorp.features.example.example2feature.presentation.entities.PresentationOperation
import com.lauruscorp.features.example.exampledomain.entities.Operation
import javax.inject.Inject

internal class OperationToPresentationOperationMapper @Inject constructor(
) : Mapper<Operation, PresentationOperation> {
	override fun map(from: Operation): PresentationOperation {
		return when (from) {
			Operation.None -> PresentationOperation.None
			Operation.Sum -> PresentationOperation.Plus
			Operation.Subtraction -> PresentationOperation.Minus
			Operation.Multiplication -> PresentationOperation.Multiply
			Operation.Division -> PresentationOperation.Divide
		}
	}
}