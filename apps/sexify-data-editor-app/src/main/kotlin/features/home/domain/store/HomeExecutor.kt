package features.home.domain.store

import application.data.test.TestTasksData
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.example.sexify_domain_core.Task
import com.lauruscorp.core_jvm.coroutines.CoroutineDispatchers
import com.lauruscorp.core_jvm.similarity.analyzeSimilarity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class HomeExecutor @Inject constructor(
    private val coroutineDispatchers: CoroutineDispatchers
) : CoroutineExecutor<HomeStore.Intent, HomeStore.Action, HomeStore.State, HomeStore.Message, HomeStore.Label>(
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
        scope.launch(coroutineDispatchers.io) {
            val tasks = TestTasksData.tasks
            val filteredTasks = withContext(Dispatchers.Default) {
                searchTasksByText(
                    tasks = tasks,
                    searchText = getState().searchText
                )
            }
            dispatch(HomeStore.Message.DataIsLoaded(tasks, filteredTasks))
        }
    }

    private fun onSearchTextChanged(newText: String, getState: () -> HomeStore.State) {
        dispatch(HomeStore.Message.ChangeSearchText(newText = newText))
        scope.launch(coroutineDispatchers.default) {
            val filteredTasks = searchTasksByText(
                tasks = getState().tasks,
                searchText = newText
            )
            dispatch(HomeStore.Message.FilteredTasksChanged(newTasks = filteredTasks))
        }
    }

    private fun searchTasksByText(
        tasks: List<Task>,
        searchText: String
    ): List<Task> {
        return if (searchText.isNotBlank()) {
            tasks.filter { task ->
                task.text.analyzeSimilarity(searchText) >= SIMILARITY_RATIO
            }
        } else {
            tasks
        }
    }

    companion object {
        private const val SIMILARITY_RATIO = 0.4f
    }
}