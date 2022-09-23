package features.task_editor.domain.repository

import com.lauruscorp.sexify_data.sex.SexifySex

interface SexesRepository {
	suspend fun getAllSexes(): List<SexifySex>
}