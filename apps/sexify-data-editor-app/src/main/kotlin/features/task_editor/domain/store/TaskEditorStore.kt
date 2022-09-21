package features.task_editor.domain.store

import com.arkivanov.mvikotlin.core.store.Store
import com.lauruscorp.sexify_data.languages.SexifyLanguage
import com.lauruscorp.sexify_data.sex.SexifySex
import features.task_editor.domain.entities.LoadingState
import features.task_editor.domain.entities.Task

interface TaskEditorStore : Store<TaskEditorStore.Intent, TaskEditorStore.State, Any> {
	sealed interface Intent { // TODO: Not implemented yet
	
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
		val availableStages: List<Task.Stage>,
		val stage: Task.Stage?,
		val availableSexes: List<SexifySex>,
		val doerSexes: List<SexifySex>,
		val partnerSexes: List<SexifySex>,
		val timerSec: Int? = null
	)
	
	sealed interface Message {
		object StartedLoading : Message
		object FinishedLoadingDataForCreation : Message // TODO: Not implemented yet
		data class FinishedLoadingDataForEditing(
			val id: Long?,
			val originalText: String,
			val originalTextLanguage: SexifyLanguage,
			val textTranslations: Map<SexifyLanguage, String>,
			val availableStages: List<Task.Stage>,
			val stage: Task.Stage?,
			val availableSexes: List<SexifySex>,
			val doerSexes: List<SexifySex>,
			val partnerSexes: List<SexifySex>,
			val timerSec: Int? = null
		) : Message
		
		data class FailedToLoadData(val message: String) : Message
	}
}