package features.task_editor.presentation.entities

import androidx.compose.runtime.Immutable
import features.task_editor.domain.entities.Task

@Immutable
data class TaskStageSelectorUiData(
	val selectedStage: Task.Stage?,
	val availableStages: List<Task.Stage>,
	val error: String? = null
)