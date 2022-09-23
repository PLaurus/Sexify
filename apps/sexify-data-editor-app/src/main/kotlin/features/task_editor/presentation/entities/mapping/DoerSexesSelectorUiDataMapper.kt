package features.task_editor.presentation.entities.mapping

import features.task_editor.domain.entities.TaskEditorError
import features.task_editor.domain.store.TaskEditorStore
import features.task_editor.presentation.entities.SexesSelectorUiData

fun TaskEditorStore.State.toDoerSexesSelectorUiData(): SexesSelectorUiData {
	return SexesSelectorUiData(
		sexes = availableSexes,
		selectedSexes = doerSexes,
		error = getDoerSexesError()
	)
}

private fun TaskEditorStore.State.getDoerSexesError(): String? {
	val error = errors.filterIsInstance<TaskEditorError.DoerSexesError>()
		.firstOrNull() ?: return null
	
	return when (error) {
		is TaskEditorError.DoerSexesError.NotSelected -> {
			"Дожен быть выбран как минимум один допустимый пол выполняющего"
		}
		is TaskEditorError.DoerSexesError.DbSexDoesNotExist -> {
			"Выбранный пол (${error.sex}) не существует в базе данных"
		}
	}
}