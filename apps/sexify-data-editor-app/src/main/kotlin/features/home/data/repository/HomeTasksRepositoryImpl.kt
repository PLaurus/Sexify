package features.home.data.repository

import com.lauruscorp.core_jvm.coroutines.CoroutineDispatchers
import com.lauruscorp.sexify_data.database.SexifyDatabase
import com.lauruscorp.sexify_data.database.utils.flowSelectAllTasks
import com.lauruscorp.sexify_data.database.utils.getDescription
import com.lauruscorp.sexify_data.database.utils.getDoerPartnerSexes
import com.lauruscorp.sexify_data.database.utils.getDoerSexes
import com.lauruscorp.sexify_data.database.utils.getName
import com.lauruscorp.sexify_data.database.utils.getTaskStage
import com.lauruscorp.sexify_data.database.utils.getText
import com.lauruscorp.sexify_data.database.utils.selectAllTasks
import features.home.data.mapping.toHomeSex
import features.home.domain.entities.HomeTask
import features.home.domain.repository.TasksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

private typealias DbTask = com.lauruscorp.sexifydata.database.tables.Task

internal class TasksRepositoryImpl @Inject constructor(
	private val database: SexifyDatabase,
	private val coroutineDispatchers: CoroutineDispatchers
) : TasksRepository {
	override suspend fun getTasks(
		languageId: String
	): List<HomeTask> = withContext(coroutineDispatchers.io) {
		database.transactionWithResult {
			database.selectAllTasks()
				.mapNotNull { it.toDomainTask(languageId, useTransaction = false) }
		}
	}
	
	override fun getTasksFlow(languageId: String): Flow<List<HomeTask>> {
		return database.flowSelectAllTasks()
			.map { dbTasks ->
				database.transactionWithResult {
					dbTasks.mapNotNull { it.toDomainTask(languageId, useTransaction = false) }
				}
			}
			.flowOn(coroutineDispatchers.io)
	}
	
	private fun DbTask.toDomainTask(
		languageId: String,
		useTransaction: Boolean = true
	): HomeTask? {
		fun map(): HomeTask? {
			val dbTaskStage = getTaskStage(database) ?: return null
			
			return HomeTask(
				id = id,
				text = getText(database, languageId) ?: return null,
				stage = HomeTask.Stage(
					id = dbTaskStage.id,
					name = dbTaskStage.getName(database, languageId) ?: return null,
					description = dbTaskStage.getDescription(database, languageId)
				),
				doerSexes = getDoerSexes(database)
					.mapNotNull { it.toHomeSex() },
				partnerSexes = getDoerPartnerSexes(database)
					.mapNotNull { it.toHomeSex() }
			)
		}
		
		return if (useTransaction) {
			database.transactionWithResult {
				map()
			}
		} else {
			map()
		}
	}
}