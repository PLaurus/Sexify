package features.home.domain.store

import com.arkivanov.mvikotlin.core.store.Store
import features.home.domain.entities.HomeTask

interface HomeStore : Store<HomeStore.Intent, HomeStore.State, Any> {
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
        val tasks: List<HomeTask>,
        val sortedTasks: List<HomeTask>
    )

    sealed interface Message {
        data class DataIsLoaded(
            val tasks: List<HomeTask>,
            val sortedTasks: List<HomeTask>
        ) : Message
    
        data class ChangeSearchText(val newText: String) : Message
        data class ChangeSortedTasks(val newTasks: List<HomeTask>) : Message
    }
}