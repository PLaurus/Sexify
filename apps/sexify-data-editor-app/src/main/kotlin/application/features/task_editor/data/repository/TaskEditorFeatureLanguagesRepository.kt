package application.features.task_editor.data.repository

import com.lauruscorp.sexify_data.entities.SexifyLanguage
import features.task_editor.domain.repository.LanguagesRepository
import javax.inject.Inject

internal class TaskEditorFeatureLanguagesRepository @Inject constructor(
	private val appLanguagesRepository: com.lauruscorp.sexify_data.repositories.languages.LanguagesRepository
) : LanguagesRepository {
	override suspend fun readAll(): List<SexifyLanguage> = appLanguagesRepository.readAll()
	override suspend fun read(id: String): SexifyLanguage? = appLanguagesRepository.read(id)
}