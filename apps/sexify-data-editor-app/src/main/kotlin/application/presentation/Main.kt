import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.singleWindowApplication
import application.data.test.TestTasksData
import application.presentation.values.Dimens
import application.presentation.values.strings.StringsRu
import ui.theme.SexifyDataEditorAppTheme

fun main() = singleWindowApplication {
    SexifyDataEditorAppTheme {
        AppUi()
    }
}

@Composable
fun AppUi(
    modifier: Modifier = Modifier
) {
    var searchText by remember { mutableStateOf("") }
    val taskCardsState = remember { TestTasksData.tasks }

    Column(
        modifier = Modifier
            .padding(Dimens.LayoutSpacing8)
            .then(modifier)
    ) {
        SearchField(
            text = searchText,
            onTextChange = { searchText = it },
            modifier = Modifier.fillMaxWidth()
        )

        TaskCards(
            modifier = Modifier
                .fillMaxWidth()
                .weight(weight = 1f, fill = true),
            contentPadding = PaddingValues(Dimens.LayoutSpacing32)
        )
    }
}

@Composable
fun SearchField(
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
fun TaskCards(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        contentPadding = contentPadding
    ) {
//        items(state.taskCardsStates) { taskCardState ->
//            TaskCard(
//                modifier = Modifier.fillMaxWidth()
//            )
//        }
    }
}

@Composable
fun TaskCard(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier

            .then(modifier)
    ) { }
}

@Composable
@Preview
fun AppUiPreview() {
    SexifyDataEditorAppTheme {
        AppUi()
    }
}