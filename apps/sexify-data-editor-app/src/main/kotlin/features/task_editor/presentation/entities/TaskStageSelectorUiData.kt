package features.task_editor.presentation.entities

import androidx.compose.runtime.Immutable
import features.task_editor.domain.entities.TaskEditorTask

@Immutable
data class TaskStageSelectorUiData(
    val selectedStage: TaskEditorTask.Stage?,
    val availableStages: List<TaskEditorTask.Stage>,
    val error: String? = null
)