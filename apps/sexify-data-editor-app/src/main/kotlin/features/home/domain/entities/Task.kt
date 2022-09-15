package features.home.domain.entities

data class Task(
	val id: Long,
	val text: String,
	val stage: Stage,
	val doerSexes: List<Sex>,
	val partnerSexes: List<Sex>,
	val timerSec: Int? = null
) {
	data class Stage(
		val id: Long,
		val name: String,
		val description: String? = null
	)
}