package features.task_editor.domain.repository

import com.lauruscorp.sexify_data.languages.SexifyLanguage

interface LanguagesRepository {
	suspend fun getLanguages(): List<SexifyLanguage>
	suspend fun getLanguageById(id: String): SexifyLanguage?
}