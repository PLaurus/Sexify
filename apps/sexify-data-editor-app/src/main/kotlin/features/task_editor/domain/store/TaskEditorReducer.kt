package features.task_editor.domain.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.lauruscorp.core_jvm.collections.updated
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
			is TaskEditorStore.Message.FinishedLoadingData -> copy(
				dataLoadingState = LoadingState.Loaded,
				originalText = msg.originalText,
				originalTextLanguage = msg.originalTextLanguage,
				textTranslations = msg.textTranslations,
				availableStages = msg.availableTaskStages,
				stage = msg.taskStage,
				availableSexes = msg.availableSexes,
				doerSexes = msg.doerSexes,
				partnerSexes = msg.partnerSexes,
				timerSec = msg.timerSec
			)
			is TaskEditorStore.Message.FailedToLoadData -> copy(
				dataLoadingState = LoadingState.Error(message = msg.message)
			)
			is TaskEditorStore.Message.UpdateOriginalText -> copy(
				originalText = msg.text
			)
			is TaskEditorStore.Message.UpdateTextTranslation -> copy(
				textTranslations = textTranslations.updated(
					key = msg.language,
					newValue = msg.text
				)
			)
			is TaskEditorStore.Message.UpdateStage -> copy(
				stage = msg.stage
			)
			is TaskEditorStore.Message.UpdateDoerSexes -> copy(
				doerSexes = msg.sexes
			)
			is TaskEditorStore.Message.UpdatePartnerSexes -> copy(
				partnerSexes = msg.sexes
			)
			is TaskEditorStore.Message.UpdateTimer -> copy(
				timerSec = msg.timeSec
			)
			is TaskEditorStore.Message.UpdateErrors -> copy(
				errors = msg.errors
			)
			is TaskEditorStore.Message.SavedTask -> copy(
				isFinished = true
			)
			is TaskEditorStore.Message.DeletedTask -> copy(
				isFinished = true
			)
		}
	}
}