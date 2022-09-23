package features.home.presentation.entities

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.arkivanov.mvikotlin.extensions.coroutines.states
import features.home.di.component.HomeComponent
import features.home.di.component.dependencies.HomeFeatureDependencies
import features.home.domain.store.HomeStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Stable
class HomeWindowState(
    dependencies: HomeFeatureDependencies,
    val scope: CoroutineScope,
    val onTaskCardClicked: (taskId: Long) -> Unit = {},
    val onAddTaskClicked: () -> Unit = {},
    val onCloseRequest: () -> Unit = {}
) {
    private val homeComponent = HomeComponent(dependencies)
    
    private val homeStore = homeComponent.getHomeStore()
    private val initialStoreState = homeComponent.getHomeStoreInitialState()
    
    var searchText by mutableStateOf(initialStoreState.searchText)
        private set
    
    var tasks by mutableStateOf(initialStoreState.tasks)
        private set
    
    var sortedTasks by mutableStateOf(initialStoreState.sortedTasks)
        private set
    
    init {
        scope.launch {
            homeStore.states
                .collect { state ->
                    searchText = state.searchText
                    tasks = state.tasks
                    sortedTasks = state.sortedTasks
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
    onTaskCardClicked: (taskId: Long) -> Unit = {},
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