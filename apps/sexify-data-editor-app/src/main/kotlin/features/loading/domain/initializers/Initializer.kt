package features.loading.domain.initializers

interface Initializer {
	val dependencies: List<Class<out Initializer>>
	suspend fun initialize()
}