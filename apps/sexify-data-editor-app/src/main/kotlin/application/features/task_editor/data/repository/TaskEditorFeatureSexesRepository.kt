package application.features.task_editor.data.repository

import com.lauruscorp.sexify_data.entities.SexifySex
import features.task_editor.domain.repository.SexesRepository
import javax.inject.Inject

internal class TaskEditorFeatureSexesRepository @Inject constructor(
	private val appSexesRepository: com.lauruscorp.sexify_data.repositories.sexes.SexesRepository
) : SexesRepository {
	override suspend fun readAll(): List<SexifySex> = appSexesRepository.readAll()
}