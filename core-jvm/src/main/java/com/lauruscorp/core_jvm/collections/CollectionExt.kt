package com.lauruscorp.core_jvm.collections

@Deprecated(message = "use same method from :core module")
fun <T> Iterable<T>.updated(old: T, new: T): List<T> = map { if (it == old) new else it }

@Deprecated(message = "use same method from :core module")
fun <T> Iterable<T>.updated(index: Int, new: T): List<T> =
    mapIndexed { i, e -> if (i == index) new else e }

@Deprecated(message = "use same method from :core module")
fun <K, V> Map<K, V>.updated(key: K, newValue: V): Map<K, V> = toMutableMap().apply {
    set(key, newValue)
}