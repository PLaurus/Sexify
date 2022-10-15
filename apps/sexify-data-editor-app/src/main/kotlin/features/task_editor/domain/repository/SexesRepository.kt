package features.task_editor.domain.repository

import com.lauruscorp.sexify_data.entities.SexifySex

interface SexesRepository {
	suspend fun readAll(): List<SexifySex>
}