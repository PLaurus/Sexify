package com.lauruscorp.core_jvm.util

/**
 * A discriminated union that encapsulates a successful outcome with a value of type [T]
 * or a failure with an error of type [E].
 */
@Deprecated(message = "use same class from :core module")
sealed class Result<out T, out E> {

    @PublishedApi
    internal data class Success<out T, out E>(val value: T) : Result<T, E>()

    @PublishedApi
    internal data class Failure<out T, out E>(val error: E) : Result<T, E>()

    // discovery

    /**
     * Returns `true` if this instance represents a successful outcome.
     * In this case [isFailure] returns `false`.
     */
    val isSuccess: Boolean get() = this is Success

    /**
     * Returns `true` if this instance represents a failed outcome.
     * In this case [isSuccess] returns `false`.
     */
    val isFailure: Boolean get() = this is Failure

    // value & error retrieval

    /**
     * Returns the encapsulated value if this instance represents [success][Result.isSuccess] or
     * `null` if it is [failure][Result.isFailure].
     *
     * This function is a shorthand for `getOrElse { null }` (see [getOrElse]) or
     * `fold(onSuccess = { it }, onFailure = { null })` (see [fold]).
     */
    inline fun getOrNull(): T? {
        return when (this) {
            is Failure -> null
            is Success -> value
        }
    }

    /**
     * Returns the encapsulated error if this instance represents [failure][isFailure] or `null`
     * if it is [success][isSuccess].
     *
     * This function is a shorthand for `fold(onSuccess = { null }, onFailure = { it })`
     * (see [fold]).
     */
    inline fun errorOrNull(): E? {
        return when (this) {
            is Failure -> error
            else -> null
        }
    }

    /**
     * Companion object for [Result] class that contains its constructor functions
     * [success] and [failure].
     */
    companion object {
        /**
         * Returns an instance that encapsulates the given [value] as successful value.
         */
        @JvmName("success")
        inline fun <R, E> success(value: R): Result<R, E> = Success(value)

        /**
         * Returns an instance that encapsulates the given [error] as failure.
         */
        @JvmName("failure")
        inline fun <R, E> failure(error: E): Result<R, E> = Failure(error)
    }
}

/**
 * Throws exception if the result is failure. This internal function minimizes
 * inlined bytecode for [getOrThrow] and makes sure that in the future we can
 * add some exception-augmenting logic here (if needed).
 */
@PublishedApi
internal fun <E : Throwable> Result<*, E>.throwOnFailure() {
    if (this is Result.Failure) throw error
}

/**
 * Calls the specified function [block] and returns its encapsulated result if invocation was
 * successful, catching any [Throwable] exception that was thrown from the [block] function
 * execution and encapsulating it as a failure.
 */
fun <R> runCatching(block: () -> R): Result<R, Throwable> {
    return try {
        Result.success(block())
    } catch (e: Throwable) {
        Result.failure(e)
    }
}

/**
 * Calls the specified function [block] with `this` value as its receiver and returns its
 * encapsulated result if invocation was successful, catching any [Throwable] exception that was
 * thrown from the [block] function execution and encapsulating it as a failure.
 */
inline fun <T, R> T.runCatching(block: T.() -> R): Result<R, Throwable> {
    return try {
        Result.success(block())
    } catch (e: Throwable) {
        Result.failure(e)
    }
}

// -- extensions ---

/**
 * Returns the encapsulated value if this instance represents [success][Result.isSuccess] or
 * throws the encapsulated [Throwable] error if it is [failure][Result.isFailure].
 *
 * This function is a shorthand for `getOrElse { throw it }` (see [getOrElse]).
 */
inline fun <T, E : Throwable> Result<T, E>.getOrThrow(): T {
    return when (this) {
        is Result.Failure -> throw error
        is Result.Success -> value
    }
}

/**
 * Returns the encapsulated value if this instance represents [success][Result.isSuccess] or the
 * result of [onFailure] function for the encapsulated error if it is [failure][Result.isFailure].
 *
 * Note, that this function rethrows any [Throwable] exception thrown by [onFailure] function.
 *
 * This function is a shorthand for `fold(onSuccess = { it }, onFailure = onFailure)` (see [fold]).
 */
inline fun <R, T : R, E> Result<T, E>.getOrElse(onFailure: (error: E) -> R): R {
    return when (this) {
        is Result.Success -> value
        is Result.Failure -> onFailure(error)
    }
}

/**
 * Returns the encapsulated value if this instance represents [success][Result.isSuccess] or the
 * [defaultValue] if it is [failure][Result.isFailure].
 *
 * This function is a shorthand for `getOrElse { defaultValue }` (see [getOrElse]).
 */
inline fun <R, T : R, E> Result<T, E>.getOrDefault(defaultValue: R): R {
    return when (this) {
        is Result.Success -> value
        is Result.Failure -> defaultValue
    }
}

/**
 * Returns the result of [onSuccess] for the encapsulated value if this instance represents
 * [success][Result.isSuccess] or the result of [onFailure] function for the encapsulated error
 * if it is [failure][Result.isFailure].
 *
 * Note, that this function rethrows any [Throwable] exception thrown by [onSuccess]
 * or by [onFailure] function.
 */
inline fun <R, T, E> Result<T, E>.fold(
    onSuccess: (value: T) -> R,
    onFailure: (error: E) -> R
): R {
    return when (this) {
        is Result.Success -> onSuccess(value)
        is Result.Failure -> onFailure(error)
    }
}

// transformation

/**
 * Returns the encapsulated result of the given [transform] function applied to the encapsulated
 * value if this instance represents [success][Result.isSuccess] or the original encapsulated
 * error if it is [failure][Result.isFailure].
 *
 * Note, that this function rethrows any [Throwable] exception thrown by [transform] function.
 * See [mapCatching] for an alternative that encapsulates exceptions.
 */
inline fun <R, T, E> Result<T, E>.map(transform: (value: T) -> R): Result<R, E> {
    return when (this) {
        is Result.Success -> Result.success(transform(value))
        is Result.Failure -> Result.failure(error)
    }
}

/**
 * Returns the encapsulated result of the given [transform] function applied to the encapsulated
 * value if this instance represents [success][Result.isSuccess] or the original encapsulated
 * [Throwable] exception if it is [failure][Result.isFailure].
 *
 * This function catches any [Throwable] exception thrown by [transform] function and encapsulates
 * it as a failure. See [map] for an alternative that rethrows exceptions from `transform` function.
 */
inline fun <R, T, E : Throwable> Result<T, E>.mapCatching(transform: (value: T) -> R): Result<R, Throwable> {
    return when (this) {
        is Result.Success -> runCatching { transform(value) }
        is Result.Failure -> Result.failure(error)
    }
}

/**
 * Returns the encapsulated result of the given [transform] function applied to the encapsulated
 * error if this instance represents [failure][Result.isFailure] or the original encapsulated value
 * if it is [success][Result.isSuccess].
 *
 * Note, that this function rethrows any [Throwable] exception thrown by [transform] function.
 * See [recoverCatching] for an alternative that encapsulates exceptions.
 */
inline fun <R, T : R, E> Result<T, E>.recover(transform: (error: E) -> R): Result<R, E> {
    return when (this) {
        is Result.Success -> this
        is Result.Failure -> Result.success(transform(error))
    }
}

/**
 * Returns the encapsulated result of the given [transform] function applied to the encapsulated
 * [Throwable] exception if this instance represents [failure][Result.isFailure] or the original
 * encapsulated value if it is [success][Result.isSuccess].
 *
 * This function catches any [Throwable] exception thrown by [transform] function and encapsulates
 * it as a failure. See [recover] for an alternative that rethrows exceptions.
 */
inline fun <R, T : R, E : Throwable> Result<T, E>.recoverCatching(
    transform: (error: E) -> R
): Result<R, Throwable> {
    return when (this) {
        is Result.Success -> this
        is Result.Failure -> runCatching { transform(error) }
    }
}

// "peek" onto value/error and pipe

/**
 * Performs the given [action] on the encapsulated error if this instance represents
 * [failure][Result.isFailure]. Returns the original `Result` unchanged.
 */
inline fun <T, E> Result<T, E>.onFailure(action: (error: E) -> Unit): Result<T, E> {
    errorOrNull()?.let { action(it) }
    return this
}

/**
 * Performs the given [action] on the encapsulated value if this instance represents
 * [success][Result.isSuccess]. Returns the original `Result` unchanged.
 */
inline fun <T, E> Result<T, E>.onSuccess(action: (value: T) -> Unit): Result<T, E> {
    if (this is Result.Success) action(value)
    return this
}