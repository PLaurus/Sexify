package application.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.application
import application.presentation.entities.ApplicationState
import application.presentation.entities.rememberApplicationState
import features.home.presentation.HomeWindow
import features.task_editor.presentation.TaskEditorWindow
import ui.theme.SexifyDataEditorAppTheme

fun main() = application {
    val applicationState = rememberApplicationState(
        scope = rememberCoroutineScope()
    )

    SexifyDataEditorAppTheme {
        HomeWindow(state = applicationState.homeWindowState)
        TaskEditorWindows(applicationState = applicationState)
    }

    if (applicationState.exitApplication) {
        exitApplication()
    }
}

@Composable
private fun TaskEditorWindows(applicationState: ApplicationState) {
    for (windowState in applicationState.taskEditorWindowStates) {
        key(windowState) {
            TaskEditorWindow(windowState)
        }
    }
}