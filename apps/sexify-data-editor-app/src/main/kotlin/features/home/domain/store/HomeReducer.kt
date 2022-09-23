package features.home.domain.store

import com.arkivanov.mvikotlin.core.store.Reducer
import javax.inject.Inject

internal class HomeReducer @Inject constructor(
) : Reducer<HomeStore.State, HomeStore.Message> {
	override fun HomeStore.State.reduce(
		msg: HomeStore.Message
	): HomeStore.State {
		return when (msg) {
			is HomeStore.Message.DataIsLoaded -> copy(
				tasks = msg.tasks,
				sortedTasks = msg.sortedTasks
			)
			is HomeStore.Message.ChangeSearchText -> copy(
				searchText = msg.newText
			)
			is HomeStore.Message.ChangeSortedTasks -> copy(
				sortedTasks = msg.newTasks
			)
		}
	}
}