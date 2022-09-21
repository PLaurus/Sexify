package features.home.presentation

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import application.presentation.values.Dimens
import application.presentation.values.strings.StringsRu
import features.home.data.repository.HomeSampleDataRepository
import features.home.domain.entities.HomeTask
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
    tasks: List<HomeTask>,
    onTaskCardClicked: (taskId: Long) -> Unit,
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
            tasks = HomeSampleDataRepository.getTasks(),
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
    tasks: List<HomeTask>,
    onTaskCardClicked: (taskId: Long) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    val state = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        state = state,
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(Dimens.LayoutSpacing8),
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

    LaunchedEffect(tasks) {
        state.scrollToItem(0)
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
    task: HomeTask,
    onClicked: (taskId: Long) -> Unit,
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