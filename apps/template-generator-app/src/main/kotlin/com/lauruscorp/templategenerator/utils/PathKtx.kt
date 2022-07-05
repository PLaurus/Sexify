package com.lauruscorp.templategenerator.utils

import net.pearx.kasechange.toPascalCase
import java.nio.file.Files
import java.nio.file.LinkOption
import java.nio.file.Path
import java.nio.file.attribute.FileAttribute
import kotlin.io.path.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.exists
import kotlin.io.path.isDirectory

/**
 * Creates really existing path.
 * If some directories in the path are missing, then they will be created automatically.
 */
@Suppress("FunctionName")
fun ExistingPath(base: String, vararg subpaths: String): Path {
	val path = Path(base, *subpaths)
	path.toExistingPath()
	return path
}

fun Path.toExistingPath(): Path {
	createDirectories()
	return this
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

fun Path.replace(replacePart: (part: Path) -> Path): Path {
	val pathBuilder = PathBuilder()
	
	forEach { part ->
		val newPart = replacePart(part)
		pathBuilder.append(newPart)
	}
	
	return pathBuilder.build()
}

fun Path.replace(oldPart: Path, newPart: Path): Path {
	return replace { part ->
		if (part == oldPart) {
			newPart
		} else {
			part
		}
	}
}

fun Path.replaceTextInParts(replacer: (oldPartText: String) -> String): Path {
	return replace { part ->
		val partString = part.toString()
		val newPartString = replacer(partString)
		Path(newPartString)
	}
}

fun Path.createTempDirectory(prefix: String, vararg attrs: FileAttribute<*>): Path {
	return Files.createTempDirectory(this, prefix, *attrs)
}

class PathBuilder(path: Path? = null) {
	private val result: MutableList<Path> = path?.toMutableList() ?: mutableListOf()
	
	constructor(pathPart: String) : this(Path(pathPart))
	
	fun append(path: Path): PathBuilder {
		result.addAll(path.toList())
		return this
	}
	
	fun append(vararg pathParts: String): PathBuilder {
		val pathPartsList = pathParts.toMutableList()
		val base = pathPartsList.getOrNull(0) ?: ""
		val subpaths = pathPartsList
			.apply { removeFirstOrNull() }
			.toTypedArray()
		val path = Path(base, *subpaths)
		
		return append(path)
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

fun Path.delete(): Boolean {
	if (!exists(LinkOption.NOFOLLOW_LINKS)) {
		return false
	}
	
	if (isDirectory(LinkOption.NOFOLLOW_LINKS)) {
		getSubpaths().forEach { path ->
			if (!path.delete()) {
				return false
			}
		}
	}
	
	return try {
		toFile().delete()
	} catch (ex: UnsupportedOperationException) {
		false
	}
}

fun Path.getSubpaths(): List<Path> {
	return try {
		toFile().listFiles()
			?.map { file -> file.toPath() }
			?: emptyList()
	} catch (ex: UnsupportedOperationException) {
		emptyList()
	}
}

fun Path.toPackageString(): String {
	val packageStringBuilder = StringBuilder()
	
	val iterator = iterator()
	
	while (iterator.hasNext()) {
		val part = iterator.next()
			.toString()
		val packageStringPart = part.toPascalCase()
			.lowercase()
		
		packageStringBuilder.append(packageStringPart)
		if (iterator.hasNext()) {
			packageStringBuilder.append('.')
		}
	}
	
	return packageStringBuilder.toString()
}

fun Path.toPackageCase(): Path {
	val pathBuilder = PathBuilder()
	
	forEach { part ->
		val stringPart = part.toString()
		val stringPartInPackageCase = stringPart.toPascalCase()
			.lowercase()
		pathBuilder.append(stringPartInPackageCase)
	}
	
	return pathBuilder.build()
}

fun Path.toGradleModuleString(): String {
	val stringBuilder = StringBuilder()
	
	forEach { part ->
		val stringPart = part.toString()
		stringBuilder.append(':')
		stringBuilder.append(stringPart)
	}
	
	return stringBuilder.toString()
}