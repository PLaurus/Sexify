package features.task_editor.presentation.entities.mapping

import features.task_editor.domain.entities.TaskEditorError
import features.task_editor.domain.store.TaskEditorStore
import features.task_editor.presentation.entities.SexesSelectorUiData

fun TaskEditorStore.State.toPartnerSexesSelectorUiData(): SexesSelectorUiData {
	return SexesSelectorUiData(
		sexes = availableSexes,
		selectedSexes = partnerSexes,
		error = getPartnerSexesError()
	)
}

private fun TaskEditorStore.State.getPartnerSexesError(): String? {
	val error = errors.filterIsInstance<TaskEditorError.PartnerSexesError>()
		.firstOrNull() ?: return null
	
	return when (error) {
		is TaskEditorError.PartnerSexesError.NotSelected -> {
			"Дожен быть выбран как минимум один допустимый пол партнера"
		}
		is TaskEditorError.PartnerSexesError.DbSexDoesNotExist -> {
			"Выбранный пол (${error.sex}) не существует в базе данных"
		}
	}
}