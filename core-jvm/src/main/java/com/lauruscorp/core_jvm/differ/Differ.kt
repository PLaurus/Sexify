package com.lauruscorp.core_jvm.differ

@Deprecated(message = "use same interface from :core module")
interface Differ<in EntityT : Any> {
    fun update(value: EntityT)
}

@Deprecated(message = "use same function from :core module")
inline fun <EntityT : Any> diff(build: DifferBuilder<EntityT>.() -> Unit): Differ<EntityT> {
    val builder = DifferBuilder<EntityT>().apply {
        build()
    }

    return builder
}

@Deprecated(message = "use same class from :core module")
open class DifferBuilder<Entity : Any> : Differ<Entity> {

    @PublishedApi
    internal val differs = ArrayList<Differ<Entity>>()

    /**
     * Registers the diff strategy
     *
     * @param get a `getter` to extract a piece of data (typically a field value) from the original `Entity`
     * @param compare a `comparator` to compare a new value with the old one, default is `equals`
     * @param set a `consumer` of the values, receives the new value if it is the first value or if the `comparator`
     * returned `false`
     */
    inline fun <T> diff(
        crossinline get: (Entity) -> T,
        crossinline compare: (new: T, old: T) -> Boolean = { a, b -> a == b },
        crossinline set: (T) -> Unit
    ) {
        differs += object : Differ<Entity> {
            private var oldValue: T? = null

            override fun update(value: Entity) {
                val newValue = get(value)
                val oldValue = oldValue
                this.oldValue = newValue

                if ((oldValue == null) || !compare(newValue, oldValue)) {
                    set(newValue)
                }
            }
        }
    }

    override fun update(value: Entity) {
        differs.forEach { it.update(value) }
    }
}