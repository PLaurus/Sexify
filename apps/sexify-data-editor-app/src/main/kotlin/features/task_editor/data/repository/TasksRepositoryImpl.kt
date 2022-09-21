package features.task_editor.data.repository

import application.BuildConfig
import com.lauruscorp.core_jvm.coroutines.CoroutineDispatchers
import com.lauruscorp.sexify_data.database.SexifyDatabase
import com.lauruscorp.sexify_data.database.aliases.DbTask
import com.lauruscorp.sexify_data.database.aliases.DbTaskStage
import com.lauruscorp.sexify_data.database.aliases.DbTranslation
import com.lauruscorp.sexify_data.database.utils.getDescription
import com.lauruscorp.sexify_data.database.utils.getLanguage
import com.lauruscorp.sexify_data.database.utils.getName
import com.lauruscorp.sexify_data.database.utils.getOriginalLanguage
import com.lauruscorp.sexify_data.database.utils.getOriginalText
import com.lauruscorp.sexify_data.database.utils.getSex
import com.lauruscorp.sexify_data.database.utils.selectTaskById
import com.lauruscorp.sexify_data.database.utils.selectTaskDoerPartnerSexesByTaskId
import com.lauruscorp.sexify_data.database.utils.selectTaskDoerSexesByTaskId
import com.lauruscorp.sexify_data.database.utils.selectTaskStageById
import com.lauruscorp.sexify_data.database.utils.selectTextContentById
import com.lauruscorp.sexify_data.database.utils.selectTranslationsByTextContentId
import com.lauruscorp.sexify_data.languages.SexifyLanguage
import com.lauruscorp.sexify_data.mapping.toSexifyLanguage
import com.lauruscorp.sexify_data.mapping.toSexifySex
import com.lauruscorp.sexify_data.sex.SexifySex
import com.lauruscorp.sexifydata.database.tables.TaskDoerPartnerSex
import com.lauruscorp.sexifydata.database.tables.TaskDoerSex
import features.task_editor.domain.entities.Task
import features.task_editor.domain.repository.TasksRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class TasksRepositoryImpl @Inject constructor(
	private val database: SexifyDatabase,
	private val coroutineDispatchers: CoroutineDispatchers
) : TasksRepository {
	override suspend fun getTaskById(
		id: Long,
		language: SexifyLanguage?
	): Task? = withContext(coroutineDispatchers.io) {
		database.transactionWithResult {
			val dbTask = database.selectTaskById(id) ?: rollback(null)
			
			dbTask.mapToTask(database, languageTag = language?.tag)
		}
	}
	
	private fun DbTask.mapToTask(
		database: SexifyDatabase,
		languageTag: String? = null
	): Task {
		return Task(
			id = id,
			originalText = database.getOriginalText(textId) ?: "",
			originalTextLanguage = database.selectTextContentById(textId)
				?.getOriginalLanguage(database)
				?.toSexifyLanguage()
				?: BuildConfig.DEFAULT_SEXIFY_LANGUAGE,
			textTranslations = database.selectTranslationsByTextContentId(textId)
				.toMap(),
			stage = database.selectTaskStageById(taskStageId)
				?.toTaskStage(database, languageTag),
			doerSexes = database.selectTaskDoerSexesByTaskId(id)
				.mapNotNull { it.toSexifySex(database) },
			partnerSexes = database.selectTaskDoerPartnerSexesByTaskId(id)
				.mapNotNull { it.toSexifySex(database) },
			timerSec = timer
		)
	}
	
	private fun List<DbTranslation>.toMap(): Map<SexifyLanguage, String> {
		val result = mutableMapOf<SexifyLanguage, String>()
		
		for (dbTranslation in this) {
			val dbLanguage = dbTranslation.getLanguage(database) ?: continue
			val language = dbLanguage.toSexifyLanguage() ?: continue
			val translation = dbTranslation.translation
			result += language to translation
		}
		
		return result
	}
	
	private fun DbTaskStage.toTaskStage(
		database: SexifyDatabase,
		languageTag: String? = null
	): Task.Stage? {
		return Task.Stage(
			id = id,
			name = getName(database, languageTag) ?: return null,
			description = getDescription(database, languageTag)
		)
	}
	
	private fun TaskDoerSex.toSexifySex(
		database: SexifyDatabase
	): SexifySex? {
		return getSex(database)?.toSexifySex()
	}
	
	private fun TaskDoerPartnerSex.toSexifySex(
		database: SexifyDatabase
	): SexifySex? {
		return getSex(database)?.toSexifySex()
	}
}