package features.task_editor.presentation.entities.mapping

import features.task_editor.domain.entities.TaskEditorError
import features.task_editor.domain.store.TaskEditorStore
import features.task_editor.presentation.entities.TaskStageSelectorUiData

fun TaskEditorStore.State.toTaskStageSelectorUiData(): TaskStageSelectorUiData {
	return TaskStageSelectorUiData(
		selectedStage = stage,
		availableStages = availableStages,
		error = getTaskStageError()
	)
}

private fun TaskEditorStore.State.getTaskStageError(): String? {
	val error = errors.filterIsInstance<TaskEditorError.TaskStageError>()
		.firstOrNull() ?: return null
	
	return when (error) {
		is TaskEditorError.TaskStageError.NotSelected -> {
			"Стадия задания должна быть выбрана"
		}
		is TaskEditorError.TaskStageError.DbStageDoesNotExist -> {
			"Стадия с id ${error.stageId} не существует в базе данных"
		}
	}
}