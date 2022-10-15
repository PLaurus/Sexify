package features.task_editor.presentation

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.window.Window
import application.BuildConfig
import application.presentation.values.Dimens
import application.presentation.values.strings.StringsRu
import com.lauruscorp.sexify_data.entities.SexifyLanguage
import com.lauruscorp.sexify_data.entities.SexifySex
import features.task_editor.data.repository.TaskEditorSampleDataRepository
import features.task_editor.domain.entities.TaskEditorTask
import features.task_editor.presentation.entities.*
import ui.theme.SexifyDataEditorAppTheme

@Composable
fun TaskEditorWindow(
    uiState: TaskEditorWindowState
) {
    Window(
        onCloseRequest = uiState::requestClose,
        title = getWindowTitle(uiState.isInEditMode)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TaskEditorWindowContent(
                isLoading = uiState.isLoading,
                taskId = uiState.id,
                originalTextFieldUiData = uiState.originalTextFieldData,
                onOriginalTextChange = uiState::updateOriginalText,
                textTranslationFieldsUiData = uiState.textTranslationFieldsUiData,
                onTextTranslationChange = uiState::updateTranslation,
                taskStageSelectorUiData = uiState.taskStageSelectorUiData,
                onTaskStageClick = uiState::selectTaskStage,
                doerSexesSelectorUiData = uiState.doerSexesSelectorUiData,
                onDoerSexChipClicked = { sex, selected ->
                    if (selected) {
                        uiState.selectDoerSex(sex)
                    } else {
                        uiState.deselectDoerSex(sex)
                    }
                },
                partnerSexesSelectorUiData = uiState.partnerSexesSelectorUiData,
                onPartnerSexChipClicked = { sex, selected ->
                    if (selected) {
                        uiState.selectPartnerSex(sex)
                    } else {
                        uiState.deselectPartnerSex(sex)
                    }
                },
                timerText = uiState.timerSec,
                onTimerTextChange = uiState::updateTimerSec,
                onSaveButtonClicked = uiState::saveData,
                onDeleteTaskClicked = uiState::deleteClicked,
                modifier = Modifier.fillMaxSize()
            )
        }
    }

}

@Composable
private fun TaskEditorWindowContent(
    isLoading: Boolean,
    taskId: Long?,
    originalTextFieldUiData: OriginalTextFieldUiData,
    onOriginalTextChange: (text: String) -> Unit,
    textTranslationFieldsUiData: List<TextTranslationFieldUiData>,
    onTextTranslationChange: (language: SexifyLanguage, text: String) -> Unit,
    taskStageSelectorUiData: TaskStageSelectorUiData,
    onTaskStageClick: (taskStage: TaskEditorTask.Stage) -> Unit,
    doerSexesSelectorUiData: SexesSelectorUiData,
    onDoerSexChipClicked: (sex: SexifySex, selected: Boolean) -> Unit,
    partnerSexesSelectorUiData: SexesSelectorUiData,
    onPartnerSexChipClicked: (sex: SexifySex, selected: Boolean) -> Unit,
    timerText: String,
    onTimerTextChange: (newText: String) -> Unit,
    onSaveButtonClicked: () -> Unit,
    onDeleteTaskClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isInEditMode = remember(taskId) { taskId != null }
    
    Scaffold(
        modifier = Modifier
            .then(modifier),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = getWindowTitle(isInEditMode = isInEditMode)
                    )
                },
                actions = {
                    SaveButton(onClick = onSaveButtonClicked)
    
                    if (isInEditMode) {
                        DeleteTaskButton(
                            onClick = onDeleteTaskClicked
                        )
                    }
                }
            )
        }
    ) {
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(Dimens.LayoutSpacing8),
                verticalArrangement = Arrangement.spacedBy(Dimens.LayoutSpacing8)
            ) {
                idTextItem(id = taskId)
                originalTextFieldItem(
                    originalTextFieldUiData = originalTextFieldUiData,
                    onTextChange = onOriginalTextChange
                )
                textTranslationItems(
                    textTranslationFieldsUiData = textTranslationFieldsUiData,
                    onTranslationChange = onTextTranslationChange
                )
                taskStageSelectorItem(
                    data = taskStageSelectorUiData,
                    onTaskStageClick = onTaskStageClick
                )
                doerSexesSelectorItem(
                    data = doerSexesSelectorUiData,
                    onSexChipClicked = onDoerSexChipClicked
                )
                partnerSexesSelectorItem(
                    data = partnerSexesSelectorUiData,
                    onSexChipClicked = onPartnerSexChipClicked
                )
                item {
                    TimerField(
                        text = timerText,
                        onTextChange = onTimerTextChange,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun TaskEditorWindowContentPreview() {
    var originalTextFieldUiData by remember {
        mutableStateOf(
            OriginalTextFieldUiData(
                text = "",
                languageName = BuildConfig.DEFAULT_SEXIFY_LANGUAGE.name,
                error = null
            )
        )
    }
    
    val textTranslationFieldsUiData = remember {
        mutableStateListOf(
            TextTranslationFieldUiData(
                language = SexifyLanguage.German,
                text = "",
                error = null
            )
        )
    }
    
    var taskStageSelectorUiData by mutableStateOf(
        TaskStageSelectorUiData(
            selectedStage = null,
            availableStages = TaskEditorSampleDataRepository.getAvailableTaskStages(),
            error = null
        )
    )
    
    var doerSexesSelectorUiData by mutableStateOf(
        SexesSelectorUiData(
            sexes = SexifySex.values()
                .toList(),
            selectedSexes = emptyList(),
            error = null
        )
    )
    
    var partnerSexesSelectorUiData by mutableStateOf(
        SexesSelectorUiData(
            sexes = SexifySex.values()
                .toList(),
            selectedSexes = emptyList(),
            error = null
        )
    )
    
    var timerText by remember { mutableStateOf("") }
    
    SexifyDataEditorAppTheme {
        TaskEditorWindowContent(
            isLoading = false,
            taskId = 0,
            originalTextFieldUiData = originalTextFieldUiData,
            onOriginalTextChange = { newText ->
                originalTextFieldUiData = originalTextFieldUiData.copy(
                    text = newText
                )
            },
            textTranslationFieldsUiData = textTranslationFieldsUiData,
            onTextTranslationChange = { language: SexifyLanguage, text: String ->
                val existingData = textTranslationFieldsUiData.first { it.language == language }
                val index = textTranslationFieldsUiData.indexOf(existingData)
                val newData = existingData.copy(
                    text = text
                )
                textTranslationFieldsUiData[index] = newData
            },
            taskStageSelectorUiData = taskStageSelectorUiData,
            onTaskStageClick = { selectedTaskStage ->
                taskStageSelectorUiData = taskStageSelectorUiData.copy(
                    selectedStage = selectedTaskStage
                )
            },
            doerSexesSelectorUiData = doerSexesSelectorUiData,
            onDoerSexChipClicked = { sex, selected ->
                if (selected) {
                    doerSexesSelectorUiData = doerSexesSelectorUiData.copy(
                        selectedSexes = doerSexesSelectorUiData.selectedSexes
                            .toMutableList()
                            .apply {
                                add(sex)
                            }
                    )
                } else {
                    doerSexesSelectorUiData = doerSexesSelectorUiData.copy(
                        selectedSexes = doerSexesSelectorUiData.selectedSexes
                            .toMutableList()
                            .apply {
                                remove(sex)
                            }
                    )
                }
            },
            partnerSexesSelectorUiData = partnerSexesSelectorUiData,
            onPartnerSexChipClicked = { sex, selected ->
                if (selected) {
                    partnerSexesSelectorUiData = partnerSexesSelectorUiData.copy(
                        selectedSexes = partnerSexesSelectorUiData.selectedSexes
                            .toMutableList()
                            .apply {
                                add(sex)
                            }
                    )
                } else {
                    partnerSexesSelectorUiData = partnerSexesSelectorUiData.copy(
                        selectedSexes = partnerSexesSelectorUiData.selectedSexes
                            .toMutableList()
                            .apply {
                                remove(sex)
                            }
                    )
                }
            },
            timerText = timerText,
            onTimerTextChange = { timerText = it },
            onSaveButtonClicked = {},
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

@Composable
private fun SaveButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Default.Save,
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
    originalTextFieldUiData: OriginalTextFieldUiData,
    onTextChange: (text: String) -> Unit,
) {
    originalTextFieldItem(
        languageName = originalTextFieldUiData.languageName,
        text = originalTextFieldUiData.text,
        onTextChange = onTextChange,
        error = originalTextFieldUiData.error
    )
}

private fun LazyListScope.originalTextFieldItem(
    languageName: String,
    text: String,
    onTextChange: (text: String) -> Unit,
    error: String? = null
) {
    item {
        OriginalTextField(languageName, text, onTextChange, Modifier.fillMaxWidth(), error)
    }
}

@Composable
private fun OriginalTextField(
    originalTextFieldUiData: OriginalTextFieldUiData,
    onTextChange: (text: String) -> Unit,
    modifier: Modifier = Modifier
) {
    OriginalTextField(
        languageName = originalTextFieldUiData.languageName,
        text = originalTextFieldUiData.text,
        onTextChange = onTextChange,
        modifier = modifier,
        error = originalTextFieldUiData.error
    )
}

@Composable
private fun OriginalTextField(
    languageName: String,
    text: String,
    onTextChange: (text: String) -> Unit,
    modifier: Modifier = Modifier,
    error: String? = null
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = text,
            onValueChange = onTextChange,
            modifier = modifier,
            label = { Text(text = "Текст по умолчанию ($languageName)") },
            isError = error != null,
            maxLines = 3
        )
        
        if (error != null) {
            Text(text = error, color = MaterialTheme.colors.error)
        }
    }
}

private fun LazyListScope.textTranslationItems(
    textTranslationFieldsUiData: List<TextTranslationFieldUiData>,
    onTranslationChange: (SexifyLanguage, String) -> Unit
) {
    textTranslationsTitleItem()
    
    items(
        items = textTranslationFieldsUiData,
        key = { it.language }
    ) { textTranslationFieldUiData ->
        TextTranslationField(
            data = textTranslationFieldUiData,
            onTextChange = onTranslationChange,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

private fun LazyListScope.textTranslationsTitleItem() {
    item {
        TextTranslationsTitle(modifier = Modifier.fillMaxWidth())
    }
}

@Composable
private fun TextTranslationsTitle(
    modifier: Modifier = Modifier
) {
    Text(text = "Переводы:", modifier = modifier)
}

@Composable
private fun TextTranslationField(
    data: TextTranslationFieldUiData,
    onTextChange: (language: SexifyLanguage, text: String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextTranslationField(
        language = data.language,
        text = data.text,
        onTextChange = onTextChange,
        modifier = modifier,
        error = data.error
    )
}

@Composable
private fun TextTranslationField(
    language: SexifyLanguage,
    text: String,
    onTextChange: (language: SexifyLanguage, text: String) -> Unit,
    modifier: Modifier = Modifier,
    error: String? = null
) {
    Column(modifier) {
        OutlinedTextField(
            value = text,
            onValueChange = { newText ->
                onTextChange(language, newText)
            },
            modifier = modifier,
            label = {
                Text(text = language.name)
            },
            maxLines = 3
        )
        
        if (error != null) {
            Text(text = error, color = MaterialTheme.colors.error)
        }
    }
}

private fun LazyListScope.taskStageSelectorItem(
    data: TaskStageSelectorUiData,
    onTaskStageClick: (taskStage: TaskEditorTask.Stage) -> Unit
) {
    taskStageSelectorItem(
        selectedTaskStage = data.selectedStage,
        availableTaskStages = data.availableStages,
        onTaskStageClick = onTaskStageClick,
        error = data.error
    )
}

private fun LazyListScope.taskStageSelectorItem(
    selectedTaskStage: TaskEditorTask.Stage?,
    availableTaskStages: List<TaskEditorTask.Stage>,
    onTaskStageClick: (taskStage: TaskEditorTask.Stage) -> Unit,
    error: String? = null
) {
    item {
        TaskStageSelector(
            selectedStage = selectedTaskStage,
            availableStages = availableTaskStages,
            onChipClick = onTaskStageClick,
            modifier = Modifier.fillMaxWidth(),
            error = error
        )
    }
}

@Composable
private fun TaskStageSelector(
    data: TaskStageSelectorUiData,
    onChipClick: (taskStage: TaskEditorTask.Stage) -> Unit,
    modifier: Modifier = Modifier
) {
    TaskStageSelector(
        selectedStage = data.selectedStage,
        availableStages = data.availableStages,
        onChipClick = onChipClick,
        modifier = modifier,
        error = data.error
    )
}

@Composable
private fun TaskStageSelector(
    selectedStage: TaskEditorTask.Stage?,
    availableStages: List<TaskEditorTask.Stage>,
    onChipClick: (taskStage: TaskEditorTask.Stage) -> Unit,
    modifier: Modifier = Modifier,
    error: String? = null
) {
    Column(modifier) {
        TaskStageSelectorTitle(modifier = Modifier.fillMaxWidth())
        
        if (error != null) {
            Text(text = error, color = MaterialTheme.colors.error)
        }
        
        TaskStageSelectorChips(
            selectedStage = selectedStage,
            availableStages = availableStages,
            onChipClick = onChipClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun TaskStageSelectorTitle(
    modifier: Modifier = Modifier
) {
    Text(text = "Стадия задания:", modifier)
}

@Composable
private fun TaskStageSelectorChips(
    selectedStage: TaskEditorTask.Stage?,
    availableStages: List<TaskEditorTask.Stage>,
    onChipClick: (taskStage: TaskEditorTask.Stage) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(Dimens.LayoutSpacing8)
    ) {
        availableStages.forEach { taskStage ->
            TaskStageChip(
                isSelected = selectedStage?.id == taskStage.id,
                taskStage = taskStage,
                onClick = onChipClick
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun TaskStageChip(
    isSelected: Boolean,
    taskStage: TaskEditorTask.Stage,
    onClick: (taskStage: TaskEditorTask.Stage) -> Unit,
    modifier: Modifier = Modifier
) {
    FilterChip(
        selected = isSelected,
        onClick = { onClick(taskStage) },
        modifier = modifier,
        leadingIcon = {
            if (isSelected) {
                Icon(imageVector = Icons.Default.Check, contentDescription = null)
            }
        }
    ) {
        Text(text = taskStage.name)
    }
}

private fun LazyListScope.doerSexesSelectorItem(
    data: SexesSelectorUiData,
    onSexChipClicked: (sex: SexifySex, selected: Boolean) -> Unit,
) {
    sexesSelectorItem(
        data = data,
        onSexChipClicked = onSexChipClicked,
        title = "Допустимый пол выполняющего:"
    )
}

private fun LazyListScope.partnerSexesSelectorItem(
    data: SexesSelectorUiData,
    onSexChipClicked: (sex: SexifySex, selected: Boolean) -> Unit,
) {
    sexesSelectorItem(
        data = data,
        onSexChipClicked = onSexChipClicked,
        title = "Допустимый пол партнера:"
    )
}

private fun LazyListScope.sexesSelectorItem(
    data: SexesSelectorUiData,
    onSexChipClicked: (sex: SexifySex, selected: Boolean) -> Unit,
    title: String? = null
) {
    sexesSelectorItem(
        sexes = data.sexes,
        selectedSexes = data.selectedSexes,
        onSexChipClicked = onSexChipClicked,
        title = title,
        error = data.error
    )
}

private fun LazyListScope.sexesSelectorItem(
    sexes: List<SexifySex>,
    selectedSexes: List<SexifySex>,
    onSexChipClicked: (sex: SexifySex, selected: Boolean) -> Unit,
    title: String? = null,
    error: String? = null
) {
    item {
        SexesSelector(
            sexes = sexes,
            selectedSexes = selectedSexes,
            onSexChipClicked = onSexChipClicked,
            modifier = Modifier.fillMaxWidth(),
            title = title,
            error = error
        )
    }
}

@Composable
private fun SexesSelector(
    data: SexesSelectorUiData,
    onSexChipClicked: (sex: SexifySex, selected: Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    SexesSelector(
        sexes = data.sexes,
        selectedSexes = data.selectedSexes,
        onSexChipClicked = onSexChipClicked,
        modifier = modifier,
        error = data.error
    )
}

@Composable
private fun SexesSelector(
    sexes: List<SexifySex>,
    selectedSexes: List<SexifySex>,
    onSexChipClicked: (sex: SexifySex, selected: Boolean) -> Unit,
    modifier: Modifier = Modifier,
    title: String? = null,
    error: String? = null
) {
    Column(modifier) {
        if (title != null) {
            Text(text = title, modifier)
        }
        
        if (error != null) {
            Text(text = error, color = MaterialTheme.colors.error)
        }
        
        SexChips(
            sexes = sexes,
            selectedSexes = selectedSexes,
            onSexChipClicked = onSexChipClicked
        )
    }
}

@Composable
fun SexChips(
    sexes: List<SexifySex>,
    selectedSexes: List<SexifySex>,
    onSexChipClicked: (sex: SexifySex, selected: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(Dimens.LayoutSpacing8)
    ) {
        sexes.forEach { sex ->
            SexChip(
                isSelected = selectedSexes.contains(sex),
                sex = sex,
                onClick = onSexChipClicked
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun SexChip(
    isSelected: Boolean,
    sex: SexifySex,
    onClick: (sex: SexifySex, selected: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    FilterChip(
        selected = isSelected,
        onClick = {
            onClick(sex, !isSelected)
        },
        modifier = modifier,
        leadingIcon = {
            if (isSelected) {
                Icon(imageVector = Icons.Default.Check, contentDescription = null)
            }
        }
    ) {
        Text(text = sex.name)
    }
}

@Composable
private fun TimerField(
    text: String,
    onTextChange: (text: String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        modifier = modifier,
        label = {
            Text(text = "Время выполнения задания")
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

private fun getWindowTitle(isInEditMode: Boolean): String {
    return if (isInEditMode) {
        StringsRu.TASK_EDITOR_WINDOW_TITLE
    } else {
        StringsRu.TASK_CREATOR_WINDOW_TITLE
    }
}