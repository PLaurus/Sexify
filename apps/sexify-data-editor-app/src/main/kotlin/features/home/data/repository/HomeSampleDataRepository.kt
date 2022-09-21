package features.home.data.repository

import features.home.domain.entities.HomeSex
import features.home.domain.entities.HomeTask

object HomeSampleDataRepository {
	fun getTasks(): List<HomeTask> {
		return listOf(
			HomeTask(
				id = 0,
				text = """
					Lorem ipsum dolor sit amet, consectetur adipiscing elit.
					Suspendisse ullamcorper augue at dui facilisis laoreet.
					Pellentesque gravida vitae eros sed dictum.
				""".trimIndent(),
				stage = Stages.Preparation,
				doerSexes = listOf(HomeSex.Man, HomeSex.Woman),
				partnerSexes = listOf(HomeSex.Man),
				timerSec = 12
			),
			HomeTask(
				id = 1,
				text = """
					Aenean placerat nisl lacinia felis finibus, in convallis leo sodales. Nunc et dui massa.
					Vivamus ultrices arcu lacinia, convallis enim pellentesque, posuere leo.
				""".trimIndent(),
				stage = Stages.Preparation,
				doerSexes = listOf(HomeSex.Woman),
				partnerSexes = listOf(HomeSex.Man),
				timerSec = 1600
			),
			HomeTask(
				id = 2,
				text = """
					Praesent sit amet vulputate eros.
					In vestibulum eleifend sem, id ullamcorper velit lobortis sit amet.
					Sed egestas ex eu enim tristique bibendum.
				""".trimIndent(),
				stage = Stages.Excitement,
				doerSexes = listOf(HomeSex.Man),
				partnerSexes = listOf(HomeSex.Woman),
				timerSec = 300
			),
			HomeTask(
				id = 3,
				text = """
					Proin vel tortor ut magna ornare feugiat nec sit amet dui.
					Suspendisse pulvinar, nibh eget lacinia tempus, risus nibh varius leo,
					sit amet ultricies ex augue eu odio. Morbi diam erat, vehicula sollicitudin felis a,
					consectetur viverra mauris. Nullam scelerisque neque sed magna lobortis posuere ut et erat.
				""".trimIndent(),
				stage = Stages.Excitement,
				doerSexes = listOf(HomeSex.Woman, HomeSex.Man),
				partnerSexes = listOf(HomeSex.Man)
			),
			HomeTask(
				id = 4,
				text = """
					Vivamus convallis sapien ac lectus aliquam, sed rutrum enim dictum.
				""".trimIndent(),
				stage = Stages.AtTheLimit,
				doerSexes = listOf(HomeSex.Man),
				partnerSexes = listOf(HomeSex.Man)
			),
			HomeTask(
				id = 5,
				text = """
					Nullam nec neque fringilla, sollicitudin felis sit amet, gravida lectus.
					Donec cursus laoreet risus, sit amet sodales tortor viverra eu.
				""".trimIndent(),
				stage = Stages.AtTheLimit,
				doerSexes = listOf(HomeSex.Woman),
				partnerSexes = listOf(HomeSex.Woman),
				timerSec = 60
			),
			HomeTask(
				id = 6,
				text = """
					Quisque et massa magna. Donec a egestas tellus.
					Etiam luctus nisl sit amet velit porttitor hendrerit id ac libero.
				""".trimIndent(),
				stage = Stages.Final,
				doerSexes = listOf(HomeSex.Woman, HomeSex.Man),
				partnerSexes = listOf(HomeSex.Man, HomeSex.Woman),
				timerSec = 60
			),
			HomeTask(
				id = 7,
				text = """
					Sed in erat posuere purus faucibus consequat.
				""".trimIndent(),
				stage = Stages.Final,
				doerSexes = listOf(),
				partnerSexes = listOf(HomeSex.Woman)
			)
		)
	}
	
	object Stages {
		val Preparation = HomeTask.Stage(
			id = 0,
			name = "Подготовка",
			description = "Это тестовое описание стадии \"Подготовка\""
		)
		
		val Excitement = HomeTask.Stage(
			id = 1,
			name = "Возбуждение",
			description = "Это тестовое описание стадии \"Возбуждение\""
		)
		
		val AtTheLimit = HomeTask.Stage(
			id = 2,
			name = "На пределе",
			description = "Это тестовое описание стадии \"На пределе\""
		)
		
		val Final = HomeTask.Stage(
			id = 3,
			name = "Финал",
			description = "Это тестовое описание стадии \"Финал\""
		)
	}
}