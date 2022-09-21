package application.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.application
import application.presentation.entities.rememberApplicationState
import features.home.presentation.HomeWindow
import features.home.presentation.entities.HomeWindowState
import features.loading.presentation.LoadingWindow
import features.loading.presentation.LoadingWindowState
import features.task_editor.presentation.TaskEditorWindow
import features.task_editor.presentation.entities.TaskEditorWindowState
import ui.theme.SexifyDataEditorAppTheme

fun main() = application {
    val applicationState = rememberApplicationState(
        scope = rememberCoroutineScope()
    ).apply {
        openLoadingWindow()
    }
    
    SexifyDataEditorAppTheme {
        ControlledLoadingWindow(state = applicationState.loadingWindowState)
        ControlledHomeWindow(state = applicationState.homeWindowState)
        ControlledTaskEditorWindows(states = applicationState.taskEditorWindowStates)
    }
    
    if (applicationState.exitApplication) {
        exitApplication()
    }
}

@Composable
private fun ControlledLoadingWindow(
    state: LoadingWindowState?
) {
    if (state != null) {
        LoadingWindow(state)
    }
}

@Composable
private fun ControlledHomeWindow(
    state: HomeWindowState?
) {
    if (state != null) {
        HomeWindow(state)
    }
}

@Composable
private fun ControlledTaskEditorWindows(states: List<TaskEditorWindowState>) {
    for (windowState in states) {
        key(windowState) {
            TaskEditorWindow(windowState)
        }
    }
}