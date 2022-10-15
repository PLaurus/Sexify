package com.lauruscorp.core_jvm.mapping

@Deprecated(message = "use same interface from :core module")
interface Mapper<in From : Any, out To : Any?> {
    fun map(from: From): To
}