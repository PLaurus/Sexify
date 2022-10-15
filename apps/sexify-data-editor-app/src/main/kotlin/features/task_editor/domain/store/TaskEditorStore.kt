package features.task_editor.domain.store

import com.arkivanov.mvikotlin.core.store.Store
import com.lauruscorp.sexify_data.entities.SexifyLanguage
import com.lauruscorp.sexify_data.entities.SexifySex
import features.task_editor.domain.entities.LoadingState
import features.task_editor.domain.entities.TaskEditorError
import features.task_editor.domain.entities.TaskEditorTask

interface TaskEditorStore : Store<TaskEditorStore.Intent, TaskEditorStore.State, Any> {
	sealed interface Intent {
		data class UpdateOriginalText(val text: String) : Intent
		
		data class UpdateTextTranslation(
			val language: SexifyLanguage,
			val text: String
		) : Intent

		data class UpdateStage(val stage: TaskEditorTask.Stage) : Intent
		data class AddDoerSex(val sex: SexifySex) : Intent
		data class RemoveDoerSex(val sex: SexifySex) : Intent
		data class AddPartnerSex(val sex: SexifySex) : Intent
		data class RemovePartnerSex(val sex: SexifySex) : Intent
		data class UpdateTimer(val timeSec: Int?) : Intent
		object SaveTask : Intent
		object DeleteTask : Intent
	}
	
	sealed interface Action {
		object LoadData : Action
	}
	
	data class State(
		val dataLoadingState: LoadingState,
		val id: Long?,
		val originalText: String,
		val originalTextLanguage: SexifyLanguage,
		val textTranslations: Map<SexifyLanguage, String>,
		val availableStages: List<TaskEditorTask.Stage>,
		val stage: TaskEditorTask.Stage?,
		val availableSexes: List<SexifySex>,
		val doerSexes: List<SexifySex>,
		val partnerSexes: List<SexifySex>,
		val timerSec: Int? = null,
		val errors: List<TaskEditorError> = emptyList(),
		val isFinished: Boolean = false
	)
	
	sealed interface Message {
		object StartedLoading : Message
		
		data class FinishedLoadingData(
			val originalText: String,
			val originalTextLanguage: SexifyLanguage,
			val textTranslations: Map<SexifyLanguage, String>,
			val availableTaskStages: List<TaskEditorTask.Stage>,
			val taskStage: TaskEditorTask.Stage?,
			val availableSexes: List<SexifySex>,
			val doerSexes: List<SexifySex>,
			val partnerSexes: List<SexifySex>,
			val timerSec: Int? = null
		) : Message
		
		data class FailedToLoadData(val message: String) : Message
		data class UpdateOriginalText(val text: String) : Message
		
		data class UpdateTextTranslation(
			val language: SexifyLanguage,
			val text: String
		) : Message

		data class UpdateStage(val stage: TaskEditorTask.Stage) : Message
		data class UpdateDoerSexes(val sexes: List<SexifySex>) : Message
		data class UpdatePartnerSexes(val sexes: List<SexifySex>) : Message
		data class UpdateTimer(val timeSec: Int?) : Message
		data class UpdateErrors(val errors: List<TaskEditorError>) : Message
		object SavedTask : Message
		object DeletedTask : Message
	}
}