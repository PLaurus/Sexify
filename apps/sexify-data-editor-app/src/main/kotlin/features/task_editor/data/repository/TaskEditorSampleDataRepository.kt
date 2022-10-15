package features.task_editor.data.repository

import com.lauruscorp.sexify_data.entities.SexifyLanguage
import com.lauruscorp.sexify_data.entities.SexifySex
import features.task_editor.domain.entities.TaskEditorTask

object TaskEditorSampleDataRepository {
	fun getTasks(): List<TaskEditorTask> {
		return listOf(
			TaskEditorTask(
				id = 0,
				originalText = "Текст по умолчанию для задания 0",
				originalTextLanguage = SexifyLanguage.Russian,
				textTranslations = mapOf(
					SexifyLanguage.Russian to "Это русский перевод текста",
					SexifyLanguage.French to "Ceci est la traduction française du texte"
				),
				stage = Stages.Preparation,
				doerSexes = listOf(SexifySex.Man, SexifySex.Woman),
				partnerSexes = listOf(SexifySex.Man),
				timerSec = 12
			),
			TaskEditorTask(
				id = 1,
				originalText = "Текст по умолчанию для задания 1",
				originalTextLanguage = SexifyLanguage.Russian,
				textTranslations = mapOf(
					SexifyLanguage.Russian to "Это русский перевод текста",
					SexifyLanguage.French to "Ceci est la traduction française du texte"
				),
				stage = Stages.Preparation,
				doerSexes = listOf(SexifySex.Woman),
				partnerSexes = listOf(SexifySex.Man),
				timerSec = 1600
			),
			TaskEditorTask(
				id = 2,
				originalText = "Текст по умолчанию для задания 2",
				originalTextLanguage = SexifyLanguage.Russian,
				textTranslations = mapOf(
					SexifyLanguage.Russian to "Это русский перевод текста",
					SexifyLanguage.French to "Ceci est la traduction française du texte"
				),
				stage = Stages.Excitement,
				doerSexes = listOf(SexifySex.Man),
				partnerSexes = listOf(SexifySex.Woman),
				timerSec = 300
			),
			TaskEditorTask(
				id = 3,
				originalText = "Текст по умолчанию для задания 3",
				originalTextLanguage = SexifyLanguage.Russian,
				textTranslations = mapOf(
					SexifyLanguage.Russian to "Это русский перевод текста",
					SexifyLanguage.French to "Ceci est la traduction française du texte"
				),
				stage = Stages.Excitement,
				doerSexes = listOf(SexifySex.Woman, SexifySex.Man),
				partnerSexes = listOf(SexifySex.Man)
			),
			TaskEditorTask(
				id = 4,
				originalText = "Текст по умолчанию для задания 4",
				originalTextLanguage = SexifyLanguage.Russian,
				textTranslations = mapOf(
					SexifyLanguage.Russian to "Это русский перевод текста",
					SexifyLanguage.French to "Ceci est la traduction française du texte"
				),
				stage = Stages.AtTheLimit,
				doerSexes = listOf(SexifySex.Man),
				partnerSexes = listOf(SexifySex.Man)
			),
			TaskEditorTask(
				id = 5,
				originalText = "Текст по умолчанию для задания 5",
				originalTextLanguage = SexifyLanguage.Russian,
				textTranslations = mapOf(
					SexifyLanguage.Russian to "Это русский перевод текста",
					SexifyLanguage.French to "Ceci est la traduction française du texte"
				),
				stage = Stages.AtTheLimit,
				doerSexes = listOf(SexifySex.Woman),
				partnerSexes = listOf(SexifySex.Woman),
				timerSec = 60
			),
			TaskEditorTask(
				id = 6,
				originalText = "Текст по умолчанию для задания 6",
				originalTextLanguage = SexifyLanguage.Russian,
				textTranslations = mapOf(),
				stage = Stages.Final,
				doerSexes = listOf(SexifySex.Woman, SexifySex.Man),
				partnerSexes = listOf(SexifySex.Man, SexifySex.Woman),
				timerSec = 60
			),
			TaskEditorTask(
				id = 7,
				originalText = "Текст по умолчанию для задания 7",
				originalTextLanguage = SexifyLanguage.Russian,
				textTranslations = mapOf(
					SexifyLanguage.Russian to "Это русский перевод текста",
					SexifyLanguage.French to "Ceci est la traduction française du texte"
				),
				stage = Stages.Final,
				doerSexes = listOf(),
				partnerSexes = listOf(SexifySex.Woman)
			)
		)
	}

	fun getAvailableTaskStages(): List<TaskEditorTask.Stage> {
		return listOf(
			Stages.Preparation,
			Stages.Excitement,
			Stages.AtTheLimit,
			Stages.Final
		)
	}
	
	object Stages {
		val Preparation = TaskEditorTask.Stage(
			id = 0,
			name = "Подготовка",
			description = "Это тестовое описание стадии \"Подготовка\""
		)

		val Excitement = TaskEditorTask.Stage(
			id = 1,
			name = "Возбуждение",
			description = "Это тестовое описание стадии \"Возбуждение\""
		)

		val AtTheLimit = TaskEditorTask.Stage(
			id = 2,
			name = "На пределе",
			description = "Это тестовое описание стадии \"На пределе\""
		)

		val Final = TaskEditorTask.Stage(
			id = 3,
			name = "Финал",
			description = "Это тестовое описание стадии \"Финал\""
		)
	}
}