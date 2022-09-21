package features.home.domain.entities

data class HomeTask(
	val id: Long,
	val text: String,
	val stage: Stage,
	val doerSexes: List<HomeSex>,
	val partnerSexes: List<HomeSex>,
	val timerSec: Int? = null
) {
	data class Stage(
		val id: Long,
		val name: String,
		val description: String? = null
	)
}