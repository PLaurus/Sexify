package features.home.data.repository

import features.home.domain.entities.Sex
import features.home.domain.entities.Task

object SampleDataRepository {
	fun getTasks(): List<Task> {
		return listOf(
			Task(
				id = 0,
				text = """
					Lorem ipsum dolor sit amet, consectetur adipiscing elit.
					Suspendisse ullamcorper augue at dui facilisis laoreet.
					Pellentesque gravida vitae eros sed dictum.
				""".trimIndent(),
				stage = Stages.Preparation,
				doerSexes = listOf(Sex.Man, Sex.Woman),
				partnerSexes = listOf(Sex.Man),
				timerSec = 12
			),
			Task(
				id = 1,
				text = """
					Aenean placerat nisl lacinia felis finibus, in convallis leo sodales. Nunc et dui massa.
					Vivamus ultrices arcu lacinia, convallis enim pellentesque, posuere leo.
				""".trimIndent(),
				stage = Stages.Preparation,
				doerSexes = listOf(Sex.Woman),
				partnerSexes = listOf(Sex.Man),
				timerSec = 1600
			),
			Task(
				id = 2,
				text = """
					Praesent sit amet vulputate eros.
					In vestibulum eleifend sem, id ullamcorper velit lobortis sit amet.
					Sed egestas ex eu enim tristique bibendum.
				""".trimIndent(),
				stage = Stages.Excitement,
				doerSexes = listOf(Sex.Man),
				partnerSexes = listOf(Sex.Woman),
				timerSec = 300
			),
			Task(
				id = 3,
				text = """
					Proin vel tortor ut magna ornare feugiat nec sit amet dui.
					Suspendisse pulvinar, nibh eget lacinia tempus, risus nibh varius leo,
					sit amet ultricies ex augue eu odio. Morbi diam erat, vehicula sollicitudin felis a,
					consectetur viverra mauris. Nullam scelerisque neque sed magna lobortis posuere ut et erat.
				""".trimIndent(),
				stage = Stages.Excitement,
				doerSexes = listOf(Sex.Woman, Sex.Man),
				partnerSexes = listOf(Sex.Man)
			),
			Task(
				id = 4,
				text = """
					Vivamus convallis sapien ac lectus aliquam, sed rutrum enim dictum.
				""".trimIndent(),
				stage = Stages.AtTheLimit,
				doerSexes = listOf(Sex.Man),
				partnerSexes = listOf(Sex.Man)
			),
			Task(
				id = 5,
				text = """
					Nullam nec neque fringilla, sollicitudin felis sit amet, gravida lectus.
					Donec cursus laoreet risus, sit amet sodales tortor viverra eu.
				""".trimIndent(),
				stage = Stages.AtTheLimit,
				doerSexes = listOf(Sex.Woman),
				partnerSexes = listOf(Sex.Woman),
				timerSec = 60
			),
			Task(
				id = 6,
				text = """
					Quisque et massa magna. Donec a egestas tellus.
					Etiam luctus nisl sit amet velit porttitor hendrerit id ac libero.
				""".trimIndent(),
				stage = Stages.Final,
				doerSexes = listOf(Sex.Woman, Sex.Man),
				partnerSexes = listOf(Sex.Man, Sex.Woman),
				timerSec = 60
			),
			Task(
				id = 7,
				text = """
					Sed in erat posuere purus faucibus consequat.
				""".trimIndent(),
				stage = Stages.Final,
				doerSexes = listOf(),
				partnerSexes = listOf(Sex.Woman)
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