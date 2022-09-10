package features.home.presentation

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import application.data.test.TestTasksData
import application.presentation.values.Dimens
import application.presentation.values.strings.StringsRu
import com.example.sexify_domain_core.Task
import features.home.presentation.entities.HomeWindowState
import ui.theme.SexifyDataEditorAppTheme

@Composable
fun HomeWindow(
    state: HomeWindowState
) {
    Window(
        onCloseRequest = state.onCloseRequest,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimens.LayoutSpacing8)
        ) {
            HomeWindowContent(
                searchText = state.searchText,
                onSearchTextChange = state::changeSearchText,
                tasks = state.filteredTasks,
                onTaskCardClicked = state.onTaskCardClicked,
                onAddTaskClicked = state.onAddTaskClicked
            )
        }
    }
}

@Composable
private fun HomeWindowContent(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    tasks: List<Task>,
    onTaskCardClicked: (taskId: Int) -> Unit,
    onAddTaskClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .then(modifier)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            SearchField(
                text = searchText,
                onTextChange = onSearchTextChange,
                modifier = Modifier.fillMaxWidth()
            )

            TaskCards(
                tasks = tasks,
                onTaskCardClicked = onTaskCardClicked,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(weight = 1f, fill = true),
                contentPadding = PaddingValues(Dimens.LayoutSpacing8)
            )
        }

        CreateTaskButton(
            onClick = onAddTaskClicked,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}


@Composable
@Preview
private fun HomeScreenContentPreview() {
    SexifyDataEditorAppTheme {
        var searchText by remember { mutableStateOf("") }
        HomeWindowContent(
            searchText = searchText,
            onSearchTextChange = {
                searchText = it
            },
            tasks = TestTasksData.tasks,
            onTaskCardClicked = {},
            onAddTaskClicked = {}
        )
    }
}

@Composable
private fun SearchField(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        modifier = Modifier
            .then(modifier),
        label = {
            Text(StringsRu.SEARCH_FIELD_LABEL)
        },
        singleLine = true
    )
}

@Composable
private fun TaskCards(
    tasks: List<Task>,
    onTaskCardClicked: (taskId: Int) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(Dimens.LayoutSpacing8)
    ) {
        items(
            items = tasks,
            key = { it.id }
        ) { task ->
            TaskCard(
                task = task,
                onClicked = onTaskCardClicked,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun CreateTaskButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FloatingActionButton(
        onClick,
        modifier = Modifier
            .then(modifier)
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = null)
    }
}

@Composable
private fun TaskCard(
    task: Task,
    onClicked: (taskId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .clickable {
                onClicked(task.id)
            }
            .then(modifier),
    ) {
        Row(
            modifier = Modifier.padding(all = Dimens.LayoutSpacing8),
            horizontalArrangement = Arrangement.spacedBy(Dimens.LayoutSpacing8)
        ) {
            Text(text = "${task.id}")
            Text(text = task.text)
        }
    }
}