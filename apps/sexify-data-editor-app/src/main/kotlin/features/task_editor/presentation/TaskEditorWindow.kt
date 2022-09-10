package features.task_editor.presentation

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import application.presentation.values.Dimens
import application.presentation.values.strings.StringsRu
import features.task_editor.presentation.entities.TaskEditorWindowState
import features.task_editor.presentation.entities.rememberTaskEditorWindowState
import ui.theme.SexifyDataEditorAppTheme

@Composable
fun TaskEditorWindow(
    state: TaskEditorWindowState = rememberTaskEditorWindowState()
) {
    Window(
        onCloseRequest = { state.onExit?.invoke(state) },
        title = getWindowTitle(state.isInEditMode)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TaskEditorWindowContent(
                isEditing = state.isInEditMode,
                onDeleteTaskClicked = {
                    TODO("Not yet implemented")
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }

}

@Composable
fun TaskEditorWindowContent(
    isEditing: Boolean,
    onDeleteTaskClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = Modifier
            .then(modifier),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = getWindowTitle(isEditing)
                    )
                },
                actions = {
                    if (isEditing) {
                        DeleteTaskButton(
                            onClick = onDeleteTaskClicked
                        )
                    }
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .padding(Dimens.LayoutSpacing8)
        ) {

        }

    }
}

@Preview
@Composable
fun TaskEditorWindowContentPreview() {
    SexifyDataEditorAppTheme {
        TaskEditorWindowContent(
            isEditing = true,
            onDeleteTaskClicked = {}
        )
    }
}

@Composable
fun DeleteTaskButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.then(modifier)
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = null
        )
    }
}

private fun getWindowTitle(isInEditMode: Boolean): String {
    return if (isInEditMode) {
        StringsRu.TASK_EDITOR_WINDOW_TITLE
    } else {
        StringsRu.TASK_CREATOR_WINDOW_TITLE
    }
}