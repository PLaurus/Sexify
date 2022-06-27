package com.lauruscorp.templategenerator.utils

import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.createDirectories

/**
 * Creates really existing path.
 * If some directories in the path are missing, then they will be created automatically.
 */
@Suppress("FunctionName")
fun ExistingPath(base: String, vararg subpaths: String): Path {
	val path = Path(base, *subpaths)
	path.createDirectories()
	return path
}

fun Path.append(path: Path): Path {
	val result = toMutableList()
	
	result.addAll(path.toList())
	
	val base = result.firstOrNull()
		?.toString() ?: ""
	val subpaths = result.apply { removeFirstOrNull() }
		.map { it.toString() }
		.toTypedArray()
	
	return Path(base, *subpaths)
}

class PathBuilder(path: Path? = null) {
	private val result = path?.toMutableList() ?: mutableListOf()
	
	fun append(path: Path): PathBuilder {
		result.addAll(path.toList())
		return this
	}
	
	fun build(): Path {
		val result = this.result
		val base = result.firstOrNull()
			?.toString() ?: ""
		val subpaths = result.apply { removeFirstOrNull() }
			.map { it.toString() }
			.toTypedArray()
		
		return Path(base, *subpaths)
	}
	
	override fun toString(): String {
		return build().toString()
	}
}