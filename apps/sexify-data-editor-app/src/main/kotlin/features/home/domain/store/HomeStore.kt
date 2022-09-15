package features.home.domain.store

import com.arkivanov.mvikotlin.core.store.Store
import features.home.domain.entities.Task

interface HomeStore : Store<HomeStore.Intent, HomeStore.State, HomeStore.Label> {
    sealed interface Intent {
        data class ChangeSearchText(
            val text: String
        ) : Intent
    }

    sealed interface Action {
        object LoadData : Action
    }

    data class State(
        val searchText: String,
        val tasks: List<Task>,
        val filteredTasks: List<Task>
    )

    sealed interface Message {
        data class DataIsLoaded(
            val tasks: List<Task>,
            val filteredTasks: List<Task>
        ) : Message

        data class ChangeSearchText(val newText: String) : Message
        data class FilteredTasksChanged(val newTasks: List<Task>) : Message
    }

    sealed interface Label {

    }
}