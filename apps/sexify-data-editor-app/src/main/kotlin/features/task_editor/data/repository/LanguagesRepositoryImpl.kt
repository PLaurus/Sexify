package features.task_editor.data.repository

import com.lauruscorp.core_jvm.coroutines.CoroutineDispatchers
import com.lauruscorp.sexify_data.database.SexifyDatabase
import com.lauruscorp.sexify_data.database.utils.selectAllLanguages
import com.lauruscorp.sexify_data.database.utils.selectLanguageById
import com.lauruscorp.sexify_data.languages.SexifyLanguage
import com.lauruscorp.sexify_data.mapping.toSexifyLanguage
import features.task_editor.domain.repository.LanguagesRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class LanguagesRepositoryImpl @Inject constructor(
	private val coroutineDispatchers: CoroutineDispatchers,
	private val database: SexifyDatabase
) : LanguagesRepository {
	override suspend fun getLanguages(): List<SexifyLanguage> = withContext(coroutineDispatchers.io) {
		database.transactionWithResult {
			val dbLanguages = database.selectAllLanguages()
			dbLanguages.mapNotNull { it.toSexifyLanguage() }
		}
	}
	
	override suspend fun getLanguageById(
		id: String
	): SexifyLanguage? = withContext(coroutineDispatchers.io) {
		database.transactionWithResult {
			val dbLanguage = database.selectLanguageById(id)
			dbLanguage?.toSexifyLanguage()
		}
	}
}