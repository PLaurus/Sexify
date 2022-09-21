package features.task_editor.domain.store

import application.BuildConfig
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.lauruscorp.core_jvm.coroutines.CoroutineDispatchers
import features.task_editor.di.component.qualifiers.InvalidInitialDataStringQualifier
import features.task_editor.di.component.qualifiers.TaskEditorTaskIdQualifier
import features.task_editor.domain.entities.Language
import features.task_editor.domain.repository.TasksRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class TaskEditorExecutor @Inject constructor(
	private val coroutineDispatchers: CoroutineDispatchers,
	@TaskEditorTaskIdQualifier private val taskId: Long?,
	private val tasksRepository: TasksRepository,
	@InvalidInitialDataStringQualifier private val invalidDataMessage: String,
) : CoroutineExecutor<TaskEditorStore.Intent, TaskEditorStore.Action, TaskEditorStore.State, TaskEditorStore.Message, Any>(
	mainContext = coroutineDispatchers.main
) {
	override fun executeAction(
		action: TaskEditorStore.Action,
		getState: () -> TaskEditorStore.State
	) {
		when (action) {
			TaskEditorStore.Action.LoadData -> loadData(taskId = taskId)
		}
	}
	
	private fun loadData(
		taskId: Long?
	) {
		dispatch(TaskEditorStore.Message.StartedLoading)
		
		scope.launch {
			if (taskId != null) {
				loadDataForTaskEditing(taskId)
			} else {
				dispatch(TaskEditorStore.Message.FinishedLoadingDataForCreation)
			}
		}
	}
	
	private suspend fun loadDataForTaskEditing(
		taskId: Long
	) = withContext(coroutineDispatchers.io) {
		val task = tasksRepository.getTaskById(taskId, BuildConfig.DEFAULT_SEXIFY_LANGUAGE)
		
		if (task != null) {
			dispatch(
				TaskEditorStore.Message.FinishedLoadingDataForEditing(
					id = task.id,
					originalText = task.originalText,
					originalTextLanguage = task.originalTextLanguage,
					textTranslations = task.textTranslations,
					availableStages = emptyList(), // TODO: Not implemented yet
					stage = task.stage,
					availableSexes = emptyList(), // TODO: Not implemented yet
					doerSexes = task.doerSexes,
					partnerSexes = task.partnerSexes,
					timerSec = task.timerSec
				)
			)
		} else {
			dispatch(TaskEditorStore.Message.FailedToLoadData(invalidDataMessage))
		}
	}
	
	private fun Map<Language, String>.addMissingTranslationsFrom(
		languages: List<Language>
	): Map<Language, String> {
		val result = this.toMutableMap()
		
		languages.forEach { language ->
			if (!result.containsKey(language)) {
				result[language] = ""
			}
		}
		
		return result
	}
}