package features.task_editor.domain.repository

import com.lauruscorp.sexify_data.entities.SexifyLanguage

interface LanguagesRepository {
	suspend fun readAll(): List<SexifyLanguage>
	suspend fun read(id: String): SexifyLanguage?
}