package features.home.domain.store

import com.arkivanov.mvikotlin.core.store.Store
import com.example.sexify_domain_core.Task

interface HomeStore : Store<HomeStore.Intent, HomeStore.State, HomeStore.Label> {
    sealed interface Intent {
        data class ChangeSearchText(
            val text: String
        ) : Intent
    }

    data class State(
        val searchText: String,
        val tasks: List<Task>,
        val filteredTasks: List<Task>
    )

    sealed interface Label {

    }
}