package features.task_editor.presentation.entities

import androidx.compose.runtime.*
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.lauruscorp.sexify_data.entities.SexifyLanguage
import com.lauruscorp.sexify_data.entities.SexifySex
import features.task_editor.dependencies.TaskEditorFeatureDependencies
import features.task_editor.di.component.TaskEditorFeatureComponent
import features.task_editor.domain.entities.LoadingState
import features.task_editor.domain.entities.TaskEditorTask
import features.task_editor.domain.store.TaskEditorStore
import features.task_editor.presentation.entities.mapping.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Stable
class TaskEditorWindowState(
    dependencies: TaskEditorFeatureDependencies,
    scope: CoroutineScope,
    taskId: Long?,
    private val onCloseRequest: ((state: TaskEditorWindowState) -> Unit)? = null,
) {
    private val component = TaskEditorFeatureComponent(dependencies, taskId)
    
    private val store = component.getTaskEditorStore()
    private val initialStoreState = component.getTaskEditorStoreInitialState()
    
    var isInEditMode: Boolean by mutableStateOf(initialStoreState.id != null)
        private set
    
    var isLoading: Boolean by mutableStateOf(initialStoreState.dataLoadingState == LoadingState.Loading)
        private set
    
    var id: Long? by mutableStateOf(initialStoreState.id)
        private set
    
    var originalTextFieldData by mutableStateOf(initialStoreState.toOriginalTextFieldData())
        private set
    
    var textTranslationFieldsUiData by mutableStateOf(initialStoreState.toTextTranslationFieldsUiData())
        private set
    
    var taskStageSelectorUiData by mutableStateOf(initialStoreState.toTaskStageSelectorUiData())
        private set
    
    var doerSexesSelectorUiData by mutableStateOf(initialStoreState.toDoerSexesSelectorUiData())
        private set
    
    var partnerSexesSelectorUiData by mutableStateOf(initialStoreState.toPartnerSexesSelectorUiData())
        private set
    
    var timerSec: String by mutableStateOf(initialStoreState.timerSec?.toString() ?: "")
        private set
    
    init {
        scope.launch {
            store.states
                .collect { state ->
                    isInEditMode = state.id != null
                    isLoading = state.dataLoadingState == LoadingState.Loading
                    id = state.id
                    originalTextFieldData = state.toOriginalTextFieldData()
                    textTranslationFieldsUiData = state.toTextTranslationFieldsUiData()
                    taskStageSelectorUiData = state.toTaskStageSelectorUiData()
                    doerSexesSelectorUiData = state.toDoerSexesSelectorUiData()
                    partnerSexesSelectorUiData = state.toPartnerSexesSelectorUiData()
                    timerSec = state.timerSec?.toString() ?: ""
                    
                    if (state.isFinished) {
                        requestClose()
                    }
                }
        }
    }
    
    fun updateOriginalText(
        text: String
    ) {
        store.accept(TaskEditorStore.Intent.UpdateOriginalText(text))
    }
    
    fun updateTranslation(
        language: SexifyLanguage,
        text: String
    ) {
        store.accept(TaskEditorStore.Intent.UpdateTextTranslation(language, text))
    }
    
    fun selectTaskStage(
        taskStage: TaskEditorTask.Stage
    ) {
        store.accept(TaskEditorStore.Intent.UpdateStage(taskStage))
    }
    
    fun selectDoerSex(
        sex: SexifySex
    ) {
        store.accept(TaskEditorStore.Intent.AddDoerSex(sex))
    }
    
    fun deselectDoerSex(
        sex: SexifySex
    ) {
        store.accept(TaskEditorStore.Intent.RemoveDoerSex(sex))
    }
    
    fun selectPartnerSex(
        sex: SexifySex
    ) {
        store.accept(TaskEditorStore.Intent.AddPartnerSex(sex))
    }
    
    fun deselectPartnerSex(
        sex: SexifySex
    ) {
        store.accept(TaskEditorStore.Intent.RemovePartnerSex(sex))
    }
    
    fun updateTimerSec(timeText: String) {
        val timeSec = if (timeText.isBlank()) {
            null
        } else {
            timeText.toIntOrNull() ?: return
        }
        
        store.accept(
            TaskEditorStore.Intent.UpdateTimer(
                timeSec = timeSec
            )
        )
    }
    
    fun saveData() {
        store.accept(TaskEditorStore.Intent.SaveTask)
    }
    
    fun deleteClicked() {
        store.accept(TaskEditorStore.Intent.DeleteTask)
    }
    
    fun requestClose() {
        onCloseRequest?.invoke(this)
    }
}

@Composable
fun rememberTaskEditorWindowState(
    dependencies: TaskEditorFeatureDependencies,
    scope: CoroutineScope,
    taskId: Long? = null,
    onExit: ((state: TaskEditorWindowState) -> Unit)? = null
) = remember {
    TaskEditorWindowState(dependencies, scope, taskId, onExit)
}