package features.task_editor.data.repository

import com.lauruscorp.sexify_data.languages.SexifyLanguage
import com.lauruscorp.sexify_data.sex.SexifySex
import features.task_editor.domain.entities.Task

object TaskEditorSampleDataRepository {
	fun getTasks(): List<Task> {
		return listOf(
			Task(
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
			Task(
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
			Task(
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
			Task(
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
			Task(
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
			Task(
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
			Task(
				id = 6,
				originalText = "Текст по умолчанию для задания 6",
				originalTextLanguage = SexifyLanguage.Russian,
				textTranslations = mapOf(),
				stage = Stages.Final,
				doerSexes = listOf(SexifySex.Woman, SexifySex.Man),
				partnerSexes = listOf(SexifySex.Man, SexifySex.Woman),
				timerSec = 60
			),
			Task(
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
	
	object Stages {
		val Preparation = Task.Stage(
			id = 0,
			name = "Подготовка",
			description = "Это тестовое описание стадии \"Подготовка\""
		)
		
		val Excitement = Task.Stage(
			id = 1,
			name = "Возбуждение",
			description = "Это тестовое описание стадии \"Возбуждение\""
		)
		
		val AtTheLimit = Task.Stage(
			id = 2,
			name = "На пределе",
			description = "Это тестовое описание стадии \"На пределе\""
		)
		
		val Final = Task.Stage(
			id = 3,
			name = "Финал",
			description = "Это тестовое описание стадии \"Финал\""
		)
	}
}