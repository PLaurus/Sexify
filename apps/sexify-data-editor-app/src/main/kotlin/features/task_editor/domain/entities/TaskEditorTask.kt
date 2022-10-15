package features.task_editor.domain.entities

import com.lauruscorp.sexify_data.entities.SexifyLanguage
import com.lauruscorp.sexify_data.entities.SexifySex

/**
 * @param textTranslations value - text translation
 */
data class TaskEditorTask(
	val id: Long?,
	val originalText: String,
	val originalTextLanguage: SexifyLanguage,
	val textTranslations: Map<SexifyLanguage, String>,
	val stage: Stage?,
	val doerSexes: List<SexifySex>,
	val partnerSexes: List<SexifySex>,
	val timerSec: Int? = null
) {
	data class Stage(
		val id: Long,
		val name: String,
		val description: String? = null
	)
}