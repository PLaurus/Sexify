package application.presentation.entities

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import application.di.component.ApplicationComponent
import features.home.presentation.entities.HomeWindowState
import features.loading.presentation.LoadingWindowState
import features.task_editor.presentation.entities.TaskEditorWindowState
import kotlinx.coroutines.CoroutineScope

@Stable
internal class ApplicationState(
    private val scope: CoroutineScope,
    private val applicationComponent: ApplicationComponent = ApplicationComponent()
) {
    var exitApplication by mutableStateOf(false)
        private set
    
    var loadingWindowState: LoadingWindowState? by mutableStateOf(null)
        private set
    
    var homeWindowState by mutableStateOf<HomeWindowState?>(null)
        private set
    
    private val _taskEditorWindowStates = mutableStateListOf<TaskEditorWindowState>()
    val taskEditorWindowStates: List<TaskEditorWindowState> = _taskEditorWindowStates
    
    fun openLoadingWindow() {
        loadingWindowState = LoadingWindowState(
            dependencies = applicationComponent.getLoadingFeatureDependencies(),
            scope = scope,
            onLoadingFinished = {
                closeLoadingWindow()
                openHomeWindow()
            },
            onCloseRequest = ::exit
        )
    }
    
    private fun closeLoadingWindow() {
        loadingWindowState = null
    }
    
    private fun openTaskEditorWindow(taskId: Long? = null) {
        _taskEditorWindowStates.add(
            TaskEditorWindowState(
                dependencies = applicationComponent.getTaskEditorFeatureDependencies(),
                scope = scope,
                taskId = taskId,
                onCloseRequest = _taskEditorWindowStates::remove
            )
        )
    }
    
    private fun openHomeWindow() {
        homeWindowState = HomeWindowState(
            dependencies = applicationComponent.getHomeFeatureDependencies(),
            scope = scope,
            onTaskCardClicked = ::openTaskEditorWindow,
            onAddTaskClicked = ::openTaskEditorWindow,
            onCloseRequest = ::exit
        )
    }
    
    private fun exit() {
        exitApplication = true
    }
}

@Composable
internal fun rememberApplicationState(
    scope: CoroutineScope,
    applicationComponent: ApplicationComponent = ApplicationComponent()
) = remember {
    ApplicationState(
        scope = scope,
        applicationComponent = applicationComponent
    )
}