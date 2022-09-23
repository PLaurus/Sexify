package application.features.task_editor.data.repository

import com.lauruscorp.core_jvm.coroutines.CoroutineDispatchers
import com.lauruscorp.sexify_data.database.SexifyDatabase
import com.lauruscorp.sexify_data.database.utils.selectAllSexes
import com.lauruscorp.sexify_data.mapping.toSexifySex
import com.lauruscorp.sexify_data.sex.SexifySex
import features.task_editor.domain.repository.SexesRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class TaskEditorFeatureSexesRepository @Inject constructor(
	private val coroutineDispatchers: CoroutineDispatchers,
	private val database: SexifyDatabase
) : SexesRepository {
	override suspend fun getAllSexes(): List<SexifySex> = withContext(coroutineDispatchers.io) {
		database.selectAllSexes()
			.mapNotNull { dbSex -> dbSex.toSexifySex() }
	}
}