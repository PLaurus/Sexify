package features.task_editor.presentation.entities

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.arkivanov.mvikotlin.extensions.coroutines.states
import features.task_editor.di.component.TaskEditorFeatureComponent
import features.task_editor.di.component.dependencies.TaskEditorFeatureDependencies
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Stable
class TaskEditorWindowState(
    dependencies: TaskEditorFeatureDependencies,
    private val scope: CoroutineScope,
    taskId: Long?,
    val onExit: ((state: TaskEditorWindowState) -> Unit)? = null,
) {
    private val component = TaskEditorFeatureComponent(dependencies, taskId)
    
    private val store = component.getTaskEditorStore()
    private val initialStoreState = component.getTaskEditorStoreInitialState()
    
    var isInEditMode: Boolean by mutableStateOf(taskId != null)
        private set
    
    var id: Long? by mutableStateOf(initialStoreState.id)
        private set
    
    var originalText: String by mutableStateOf(initialStoreState.originalText)
        private set
    
    var originalTextLanguageTag: String by mutableStateOf(initialStoreState.originalTextLanguage.tag)
        private set
    
    init {
        scope.launch {
            store.states
                .collect { state ->
                    isInEditMode = state.id != null
                    id = state.id
                    originalTextLanguageTag = state.originalTextLanguage.tag
                }
        }
    }
    
    fun updateOriginalText(text: String) {
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