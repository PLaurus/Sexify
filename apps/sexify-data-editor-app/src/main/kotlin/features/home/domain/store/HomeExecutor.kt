package features.home.domain.store

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.lauruscorp.core_jvm.coroutines.CoroutineDispatchers
import com.lauruscorp.core_jvm.similarity.analyzeSimilarity
import features.home.domain.entities.HomeTask
import features.home.domain.repository.TasksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class HomeExecutor @Inject constructor(
    private val coroutineDispatchers: CoroutineDispatchers,
    private val tasksRepository: TasksRepository,
) : CoroutineExecutor<HomeStore.Intent, HomeStore.Action, HomeStore.State, HomeStore.Message, Any>(
    mainContext = coroutineDispatchers.main
) {
    override fun executeAction(action: HomeStore.Action, getState: () -> HomeStore.State) {
        when (action) {
            is HomeStore.Action.LoadData -> loadData(getState)
        }
    }

    override fun executeIntent(intent: HomeStore.Intent, getState: () -> HomeStore.State) {
        when (intent) {
            is HomeStore.Intent.ChangeSearchText -> onSearchTextChanged(
                newText = intent.text,
                getState = getState
            )
        }
    }

    private fun loadData(getState: () -> HomeStore.State) {
        scope.launch {
            tasksRepository.getTasksFlow("ru")
                .collect { tasks ->
                    val filteredTasks = withContext(Dispatchers.Default) {
                        searchTasksByText(
                            tasks = tasks,
                            searchText = getState().searchText
                        )
                    }
                
                    dispatch(HomeStore.Message.DataIsLoaded(tasks, filteredTasks))
                }
        }
    }

    private fun onSearchTextChanged(newText: String, getState: () -> HomeStore.State) {
        dispatch(HomeStore.Message.ChangeSearchText(newText = newText))
        scope.launch(coroutineDispatchers.default) {
            val filteredTasks = searchTasksByText(
                tasks = getState().tasks,
                searchText = newText
            )
            dispatch(HomeStore.Message.ChangeSortedTasks(newTasks = filteredTasks))
        }
    }
    
    private fun searchTasksByText(
        tasks: List<HomeTask>,
        searchText: String
    ): List<HomeTask> {
        return if (searchText.isNotBlank()) {
            tasks.sortedByDescending { task ->
                task.text.analyzeSimilarity(searchText)
            }
        } else {
            tasks
        }
    }
}