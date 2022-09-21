package features.task_editor.domain.store

import com.arkivanov.mvikotlin.core.store.Reducer
import features.task_editor.domain.entities.LoadingState
import javax.inject.Inject

internal class TaskEditorReducer @Inject constructor(
) : Reducer<TaskEditorStore.State, TaskEditorStore.Message> {
	override fun TaskEditorStore.State.reduce(
		msg: TaskEditorStore.Message
	): TaskEditorStore.State {
		return when (msg) {
			is TaskEditorStore.Message.StartedLoading -> copy(
				dataLoadingState = LoadingState.Loading
			)
			is TaskEditorStore.Message.FinishedLoadingDataForEditing -> copy(
				dataLoadingState = LoadingState.Loaded,
				id = msg.id,
				originalText = msg.originalText,
				originalTextLanguage = msg.originalTextLanguage,
				textTranslations = msg.textTranslations,
				availableStages = msg.availableStages,
				stage = msg.stage,
				availableSexes = msg.availableSexes,
				doerSexes = msg.doerSexes,
				partnerSexes = msg.partnerSexes,
				timerSec = msg.timerSec
			)
			is TaskEditorStore.Message.FinishedLoadingDataForCreation -> {
				TODO("Not implemented yet")
			}
			is TaskEditorStore.Message.FailedToLoadData -> copy(
				dataLoadingState = LoadingState.Error(message = msg.message)
			)
		}
	}
}