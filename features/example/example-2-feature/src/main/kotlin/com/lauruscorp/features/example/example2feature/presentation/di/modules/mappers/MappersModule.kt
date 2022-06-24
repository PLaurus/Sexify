package com.lauruscorp.features.example.example2feature.presentation.di.modules.mappers

import com.lauruscorp.core.mapping.Mapper
import com.lauruscorp.features.example.example2feature.presentation.entities.PresentationOperation
import com.lauruscorp.features.example.example2feature.presentation.entities.UiError
import com.lauruscorp.features.example.example2feature.presentation.mappers.LabelToUiErrorMapper
import com.lauruscorp.features.example.example2feature.presentation.mappers.OperationToPresentationOperationMapper
import com.lauruscorp.features.example.example2feature.presentation.mappers.PresentationOperationToOperationMapper
import com.lauruscorp.features.example.exampledomain.entities.Operation
import com.lauruscorp.features.example.exampledomain.store.ExampleStore
import dagger.Binds
import dagger.Module

@Module
internal abstract class MappersModule {
	@Binds
	abstract fun provideOperationToPresentationOperationMapper(
		mapper: OperationToPresentationOperationMapper
	): Mapper<Operation, PresentationOperation>
	
	@Binds
	abstract fun providePresentationOperationToOperationMapper(
		mapper: PresentationOperationToOperationMapper
	): Mapper<PresentationOperation, Operation>
	
	@Binds
	abstract fun provideLabelToUiErrorMapper(
		mapper: LabelToUiErrorMapper
	): Mapper<ExampleStore.Label, UiError>
}