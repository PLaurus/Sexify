package com.lauruscorp.core.kotlin

import android.os.Build

/**
 * Analog of [java.util.Collection.removeIf] but is compatible with all Android SDKs.
 *
 * Removes all of the elements of this collection that satisfy the given
 * predicate.  Errors or runtime exceptions thrown during iteration or by
 * the predicate are relayed to the caller.
 *
 * Traverses all elements of the collection using its [iterator][MutableCollection.iterator].
 * Each matching element is removed using [MutableIterator.remove]. If the collection's iterator does not
 * support removal then an `UnsupportedOperationException` will be thrown on the first matching element.
 *
 * @param filter a predicate which returns true for elements to be removed.
 * @return true if any elements were removed.
 * @throws NullPointerException if the specified filter is null.
 * @throws UnsupportedOperationException if elements cannot be removed from this collection.
 *         Implementations may throw this exception if a matching element cannot be removed or if, in general,
 *         removal is not supported.
 */
fun <E> MutableCollection<E>.compatRemoveIf(filter: (E) -> Boolean): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return this.removeIf(filter)
    } else {
        var removed = false
        val iterator = iterator()

        while (iterator.hasNext()) {
            if (filter(iterator.next())) {
                iterator.remove()
                removed = true
            }
        }

        removed
    }
}