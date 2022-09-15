package features.task_editor.presentation.entities

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember

@Stable
class TaskEditorWindowState(
    val taskId: Long?,
    val onExit: ((state: TaskEditorWindowState) -> Unit)? = null
) {
    val isInEditMode: Boolean = taskId != null
}

@Composable
fun rememberTaskEditorWindowState(
    taskId: Long? = null,
    onExit: ((state: TaskEditorWindowState) -> Unit)? = null
) = remember {
    TaskEditorWindowState(taskId, onExit)
}