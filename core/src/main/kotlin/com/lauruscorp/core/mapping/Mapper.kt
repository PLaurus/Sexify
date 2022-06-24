package com.lauruscorp.core.mapping

interface Mapper<in From : Any, out To : Any?> {
	fun map(from: From): To
}