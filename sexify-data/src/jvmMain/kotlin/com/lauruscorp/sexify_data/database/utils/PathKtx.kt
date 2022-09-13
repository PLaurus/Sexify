package com.lauruscorp.sexify_data.database.utils

import java.nio.file.Path
import kotlin.io.path.Path

internal fun Path.append(part: String): Path {
	val result = toMutableList()
	
	result.addAll(Path(part))
	
	val base = result.firstOrNull()
		?.toString() ?: ""
	val subpaths = result.apply { removeFirstOrNull() }
		.map { it.toString() }
		.toTypedArray()
	
	return Path(base, *subpaths)
}