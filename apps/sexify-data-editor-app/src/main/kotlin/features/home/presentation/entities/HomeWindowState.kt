package features.home.presentation.entities

import androidx.compose.runtime.*
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.example.sexify_domain_core.Task
import features.home.di.component.HomeComponent
import features.home.di.component.dependencies.HomeFeatureDependencies
import features.home.domain.store.HomeStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Stable
class HomeWindowState(
    dependencies: HomeFeatureDependencies,
    val scope: CoroutineScope,
    val onTaskCardClicked: (taskId: Int) -> Unit = {},
    val onAddTaskClicked: () -> Unit = {},
    val onCloseRequest: () -> Unit = {}
) {
    private val homeComponent = HomeComponent(dependencies)

    private val homeStore = homeComponent.getHomeStore()
    private val initialStoreState = homeComponent.getHomeStoreInitialState()

    private var _searchText by mutableStateOf(initialStoreState.searchText)
    val searchText: String
        get() = _searchText

    private var _tasks by mutableStateOf(initialStoreState.tasks)
    val tasks: List<Task>
        get() = _tasks

    private var _filteredTasks by mutableStateOf(initialStoreState.filteredTasks)
    val filteredTasks: List<Task>
        get() = _filteredTasks

    init {
        scope.launch {
            homeStore.states
                .collect { state ->
                    _searchText = state.searchText
                    _tasks = state.tasks
                    _filteredTasks = state.filteredTasks
                }
        }
    }

    fun changeSearchText(text: String) {
        homeStore.accept(HomeStore.Intent.ChangeSearchText(text))
    }
}

@Composable
fun rememberHomeWindowState(
    dependencies: HomeFeatureDependencies,
    scope: CoroutineScope,
    onTaskCardClicked: (taskId: Int) -> Unit = {},
    onAddTaskClicked: () -> Unit = {},
    onCloseRequest: () -> Unit = {}
) = remember {
    HomeWindowState(
        dependencies = dependencies,
        scope = scope,
        onTaskCardClicked = onTaskCardClicked,
        onAddTaskClicked = onAddTaskClicked,
        onCloseRequest = onCloseRequest
    )
}