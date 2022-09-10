package application.presentation.entities

import androidx.compose.runtime.*
import application.di.component.ApplicationComponent
import features.home.presentation.entities.HomeWindowState
import features.task_editor.presentation.entities.TaskEditorWindowState
import kotlinx.coroutines.CoroutineScope

@Stable
internal class ApplicationState(
    scope: CoroutineScope,
    applicationComponent: ApplicationComponent = ApplicationComponent()
) {
    private var _exitApplication by mutableStateOf(false)
    val exitApplication: Boolean
        get() = _exitApplication

    val homeWindowState: HomeWindowState = HomeWindowState(
        dependencies = applicationComponent.getHomeFeatureDependencies(),
        scope = scope,
        onTaskCardClicked = ::openTaskEditorWindow,
        onAddTaskClicked = ::openTaskEditorWindow,
        onCloseRequest = ::exit
    )

    private val _taskEditorWindowStates = mutableStateListOf<TaskEditorWindowState>()
    val taskEditorWindowStates: List<TaskEditorWindowState> = _taskEditorWindowStates

    fun openTaskEditorWindow(taskId: Int? = null) {
        _taskEditorWindowStates.add(
            TaskEditorWindowState(
                taskId = taskId,
                onExit = _taskEditorWindowStates::remove
            )
        )
    }

    fun exit() {
        _exitApplication = true
    }
}

@Composable
internal fun rememberApplicationState(
    scope: CoroutineScope
) = remember {
    ApplicationState(scope = scope)
}