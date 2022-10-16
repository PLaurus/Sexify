package features.task_editor.domain.store

import application.BuildConfig
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.lauruscorp.core.coroutines.CoroutineDispatchers
import com.lauruscorp.sexify_data.entities.SexifyLanguage
import com.lauruscorp.sexify_data.entities.SexifySex
import features.task_editor.di.component.qualifiers.InvalidInitialDataStringQualifier
import features.task_editor.domain.entities.TaskEditorError
import features.task_editor.domain.entities.TaskEditorTask
import features.task_editor.domain.repository.LanguagesRepository
import features.task_editor.domain.repository.SexesRepository
import features.task_editor.domain.repository.TaskStagesRepository
import features.task_editor.domain.repository.TasksRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class TaskEditorExecutor @Inject constructor(
	private val coroutineDispatchers: CoroutineDispatchers,
	private val tasksRepository: TasksRepository,
	private val languagesRepository: LanguagesRepository,
	private val taskStagesRepository: TaskStagesRepository,
	private val sexesRepository: SexesRepository,
	@InvalidInitialDataStringQualifier private val invalidDataMessage: String,
) : CoroutineExecutor<TaskEditorStore.Intent, TaskEditorStore.Action, TaskEditorStore.State, TaskEditorStore.Message, Any>(
	mainContext = coroutineDispatchers.main
) {
	override fun executeAction(
		action: TaskEditorStore.Action,
		getState: () -> TaskEditorStore.State
	) {
		when (action) {
			TaskEditorStore.Action.LoadData -> loadData(getState)
		}
	}
	
	override fun executeIntent(
		intent: TaskEditorStore.Intent,
		getState: () -> TaskEditorStore.State
	) {
		when (intent) {
			is TaskEditorStore.Intent.UpdateOriginalText -> updateOriginalText(
				text = intent.text,
				originalTextLanguage = getState().originalTextLanguage
			)
			is TaskEditorStore.Intent.UpdateTextTranslation -> updateTextTranslation(
				language = intent.language,
				text = intent.text,
				originalTextLanguage = getState().originalTextLanguage
			)
			is TaskEditorStore.Intent.UpdateStage -> updateStage(stage = intent.stage)
			is TaskEditorStore.Intent.AddDoerSex -> addDoerSex(
				sex = intent.sex,
				currentDoerSexes = getState().doerSexes
			)
			is TaskEditorStore.Intent.RemoveDoerSex -> removeDoerSex(
				sex = intent.sex,
				currentDoerSexes = getState().doerSexes
			)
			is TaskEditorStore.Intent.AddPartnerSex -> addPartnerSex(
				sex = intent.sex,
				currentPartnerSexes = getState().partnerSexes
			)
			is TaskEditorStore.Intent.RemovePartnerSex -> removePartnerSex(
				sex = intent.sex,
				currentPartnerSexes = getState().partnerSexes
			)
			is TaskEditorStore.Intent.UpdateTimer -> updateTimer(timeSec = intent.timeSec)
			is TaskEditorStore.Intent.SaveTask -> saveTask(state = getState())
			is TaskEditorStore.Intent.DeleteTask -> {
				val taskId = getState().id
				if (taskId != null) {
					deleteTask(taskId)
				}
			}
		}
		
		checkState(state = getState())
	}
	
	private fun loadData(
		getState: () -> TaskEditorStore.State
	) {
		dispatch(TaskEditorStore.Message.StartedLoading)
		
		scope.launch {
			val supportedLanguages = async { languagesRepository.readAll() }
			val supportedStages =
				async { taskStagesRepository.getAllTaskStages(BuildConfig.DEFAULT_SEXIFY_LANGUAGE) }
			val supportedSexes = async { sexesRepository.readAll() }
			
			val taskId = getState().id
			
			val message = if (taskId != null) {
				loadDataForTaskEditing(
					taskId = taskId,
					supportedLanguages = supportedLanguages.await(),
					supportedStages = supportedStages.await(),
					supportedSexes = supportedSexes.await()
				)
			} else {
				createDataForTaskCreation(
					supportedLanguages = supportedLanguages.await(),
					supportedStages = supportedStages.await(),
					supportedSexes = supportedSexes.await()
				)
			}
			
			dispatch(message)
			checkState(state = getState())
		}
	}
	
	private fun createDataForTaskCreation(
		supportedLanguages: List<SexifyLanguage>,
		supportedStages: List<TaskEditorTask.Stage>,
		supportedSexes: List<SexifySex>
	): TaskEditorStore.Message {
		val originalText = ""
		val taskStage = null
		val doerSexes = emptyList<SexifySex>()
		val partnerSexes = emptyList<SexifySex>()
		
		return TaskEditorStore.Message.FinishedLoadingData(
			originalText = originalText,
			originalTextLanguage = BuildConfig.DEFAULT_SEXIFY_LANGUAGE,
			textTranslations = emptyMap<SexifyLanguage, String>()
				.addMissingTranslationsFrom(supportedLanguages),
			availableTaskStages = supportedStages,
			taskStage = taskStage,
			availableSexes = supportedSexes,
			doerSexes = doerSexes,
			partnerSexes = partnerSexes,
			timerSec = null
		)
	}
	
	private suspend fun loadDataForTaskEditing(
		taskId: Long,
		supportedLanguages: List<SexifyLanguage>,
		supportedStages: List<TaskEditorTask.Stage>,
		supportedSexes: List<SexifySex>
	): TaskEditorStore.Message = coroutineScope {
		val task = tasksRepository.getTaskById(taskId, BuildConfig.DEFAULT_SEXIFY_LANGUAGE)
		
		if (task != null) {
			val originalText = task.originalText
			val taskStage = task.stage
			val doerSexes = task.doerSexes
			val partnerSexes = task.partnerSexes
			
			TaskEditorStore.Message.FinishedLoadingData(
				originalText = originalText,
				originalTextLanguage = task.originalTextLanguage,
				textTranslations = task.textTranslations
					.addMissingTranslationsFrom(supportedLanguages),
				availableTaskStages = supportedStages,
				taskStage = taskStage,
				availableSexes = supportedSexes,
				doerSexes = doerSexes,
				partnerSexes = partnerSexes,
				timerSec = task.timerSec
			)
		} else {
			TaskEditorStore.Message.FailedToLoadData(invalidDataMessage)
		}
	}
	
	private fun Map<SexifyLanguage, String>.addMissingTranslationsFrom(
		languages: List<SexifyLanguage>
	): Map<SexifyLanguage, String> {
		val result = this.toMutableMap()
		
		languages.forEach { language ->
			if (!result.containsKey(language)) {
				result[language] = ""
			}
		}
		
		return result
	}
	
	private fun updateOriginalText(
		text: String,
		originalTextLanguage: SexifyLanguage
	) {
		dispatch(TaskEditorStore.Message.UpdateOriginalText(text))
		dispatch(
			TaskEditorStore.Message.UpdateTextTranslation(
				language = originalTextLanguage,
				text = text
			)
		)
	}
	
	private fun updateTextTranslation(
		language: SexifyLanguage,
		text: String,
		originalTextLanguage: SexifyLanguage
	) {
		dispatch(
			TaskEditorStore.Message.UpdateTextTranslation(
				language = language,
				text = text
			)
		)
		
		if (originalTextLanguage == language) {
			dispatch(TaskEditorStore.Message.UpdateOriginalText(text))
		}
	}
	
	private fun updateStage(
		stage: TaskEditorTask.Stage
	) {
		dispatch(TaskEditorStore.Message.UpdateStage(stage = stage))
	}
	
	private fun addDoerSex(
		sex: SexifySex,
		currentDoerSexes: List<SexifySex>
	) {
		if (!currentDoerSexes.contains(sex)) {
			val newSexes = currentDoerSexes.toMutableList()
				.apply {
					add(sex)
				}
			
			dispatch(TaskEditorStore.Message.UpdateDoerSexes(newSexes))
		}
	}
	
	private fun removeDoerSex(
		sex: SexifySex,
		currentDoerSexes: List<SexifySex>
	) {
		if (currentDoerSexes.contains(sex)) {
			val newSexes = currentDoerSexes.toMutableList()
				.apply {
					remove(sex)
				}
			
			dispatch(TaskEditorStore.Message.UpdateDoerSexes(newSexes))
		}
	}
	
	private fun addPartnerSex(
		sex: SexifySex,
		currentPartnerSexes: List<SexifySex>
	) {
		if (!currentPartnerSexes.contains(sex)) {
			val newSexes = currentPartnerSexes.toMutableList()
				.apply {
					add(sex)
				}
			
			dispatch(TaskEditorStore.Message.UpdatePartnerSexes(newSexes))
		}
	}
	
	private fun removePartnerSex(
		sex: SexifySex,
		currentPartnerSexes: List<SexifySex>
	) {
		if (currentPartnerSexes.contains(sex)) {
			val newSexes = currentPartnerSexes.toMutableList()
				.apply {
					remove(sex)
				}
			
			dispatch(TaskEditorStore.Message.UpdatePartnerSexes(newSexes))
		}
	}
	
	private fun updateTimer(
		timeSec: Int?
	) {
		dispatch(TaskEditorStore.Message.UpdateTimer(timeSec))
	}
	
	private fun checkState(
		state: TaskEditorStore.State
	) {
		val result = mutableListOf<TaskEditorError?>()
		
		result += checkOriginalText(state.originalText)
		result += checkTaskStage(state.stage)
		result += checkDoerSexes(state.doerSexes)
		result += checkPartnerSexes(state.partnerSexes)
		
		dispatch(TaskEditorStore.Message.UpdateErrors(errors = result.filterNotNull()))
	}
	
	private fun checkOriginalText(
		text: String
	): TaskEditorError.OriginalTextError? {
		return if (text.isBlank()) {
			TaskEditorError.OriginalTextError.Empty
		} else {
			null
		}
	}
	
	private fun checkTaskStage(
		stage: TaskEditorTask.Stage?
	): TaskEditorError.TaskStageError? {
		return if (stage == null) {
			TaskEditorError.TaskStageError.NotSelected
		} else {
			null
		}
	}
	
	private fun checkDoerSexes(
		sexes: List<SexifySex>
	): TaskEditorError.DoerSexesError? {
		return if (sexes.isEmpty()) {
			TaskEditorError.DoerSexesError.NotSelected
		} else {
			null
		}
	}
	
	private fun checkPartnerSexes(
		sexes: List<SexifySex>
	): TaskEditorError.PartnerSexesError? {
		return if (sexes.isEmpty()) {
			TaskEditorError.PartnerSexesError.NotSelected
		} else {
			null
		}
	}
	
	private fun saveTask(state: TaskEditorStore.State) {
		scope.launch(coroutineDispatchers.default) {
			val task = TaskEditorTask(
				id = state.id,
				originalText = state.originalText,
				originalTextLanguage = state.originalTextLanguage,
				textTranslations = state.textTranslations,
				stage = state.stage,
				doerSexes = state.doerSexes,
				partnerSexes = state.partnerSexes,
				timerSec = state.timerSec
			)
			
			val error = tasksRepository.saveTask(task)
			
			val message = if (error == null) {
				TaskEditorStore.Message.SavedTask
			} else {
				val newErrors = state.errors.toMutableList()
				newErrors.add(error)
				TaskEditorStore.Message.UpdateErrors(newErrors)
			}
			
			dispatch(message)
		}
	}
	
	private fun deleteTask(
		id: Long
	) {
		scope.launch {
			tasksRepository.deleteTask(id)
			dispatch(TaskEditorStore.Message.DeletedTask)
		}
	}
}