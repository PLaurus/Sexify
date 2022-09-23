package features.task_editor.domain.entities

import com.lauruscorp.sexify_data.languages.SexifyLanguage
import com.lauruscorp.sexify_data.sex.SexifySex

interface TaskEditorError {
	object DbError : TaskEditorError
	
	sealed interface OriginalTextError : TaskEditorError {
		object Empty : OriginalTextError
		
		data class IncorrectDataForDb(
			val text: String,
			val language: SexifyLanguage
		) : OriginalTextError
		
		data class DBLanguageDoesNotExist(
			val language: SexifyLanguage
		) : OriginalTextError
	}
	
	sealed interface TranslationError : TaskEditorError {
		val language: SexifyLanguage
		
		data class DbLanguageDoesNotExist(
			override val language: SexifyLanguage
		) : TranslationError
	}
	
	sealed interface TaskStageError : TaskEditorError {
		object NotSelected : TaskStageError
		data class DbStageDoesNotExist(val stageId: Long) : TaskStageError
	}
	
	sealed interface DoerSexesError : TaskEditorError {
		object NotSelected : DoerSexesError
		data class DbSexDoesNotExist(val sex: SexifySex) : DoerSexesError
	}
	
	sealed interface PartnerSexesError : TaskEditorError {
		object NotSelected : PartnerSexesError
		data class DbSexDoesNotExist(val sex: SexifySex) : PartnerSexesError
	}
}