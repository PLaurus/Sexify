package features.task_editor.presentation

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import application.presentation.values.Dimens
import application.presentation.values.strings.StringsRu
import features.task_editor.presentation.entities.TaskEditorWindowState
import ui.theme.SexifyDataEditorAppTheme

@Composable
fun TaskEditorWindow(
    state: TaskEditorWindowState
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
                state = state,
                onDeleteTaskClicked = {
                    TODO("Not yet implemented")
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }

}

@Composable
private fun TaskEditorWindowContent(
    state: TaskEditorWindowState,
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
                        text = getWindowTitle(isInEditMode = state.isInEditMode)
                    )
                },
                actions = {
                    if (state.isInEditMode) {
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
                .padding(Dimens.LayoutSpacing8),
            verticalArrangement = Arrangement.spacedBy(Dimens.LayoutSpacing8)
        ) {
            idTextItem(id = state.id)
            originalTextFieldItem(
                languageTag = state.originalTextLanguageTag,
                text = state.originalText,
                onTextChange = state::updateOriginalText
            )
        }

    }
}

@Preview
@Composable
fun TaskEditorWindowContentPreview() {
    SexifyDataEditorAppTheme {
        TODO("Not implemented yet")
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

private fun LazyListScope.idTextItem(
    id: Long?
) {
    if (id != null) {
        item {
            IdText(id, Modifier.fillMaxWidth())
        }
    }
}

@Composable
private fun IdText(
    id: Long,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Id: $id",
        modifier = modifier
    )
}

private fun LazyListScope.originalTextFieldItem(
    languageTag: String,
    text: String,
    onTextChange: (text: String) -> Unit
) {
    item {
        OriginalTextField(languageTag, text, onTextChange, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
private fun OriginalTextField(
    languageTag: String,
    text: String,
    onTextChange: (text: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Dimens.LayoutSpacing8)
    ) {
        Text(text = "Текст по умолчанию ($languageTag):")
        TextField(
            value = text,
            onValueChange = onTextChange,
            modifier = Modifier.weight(1f)
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