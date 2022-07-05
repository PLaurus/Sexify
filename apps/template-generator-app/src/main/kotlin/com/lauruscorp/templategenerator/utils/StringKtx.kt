package com.lauruscorp.templategenerator.utils

fun String.getBlankStart(): String {
	val resultBuilder = StringBuilder()
	
	for (char in this) {
		when (char) {
			' ', '	' -> resultBuilder.append(char)
			else -> break
		}
	}
	
	return resultBuilder.toString()
}

fun String.replaceLineIfContains(
	other: String,
	replacement: String,
	preserveIndent: Boolean = false
): String {
	val resultLines = lines().toMutableList()
	val mutableIterator = resultLines.listIterator()
	
	while (mutableIterator.hasNext()) {
		val line = mutableIterator.next()
		
		if (line.contains(other)) {
			mutableIterator.remove()
			if (preserveIndent) {
				val indent = line.getBlankStart()
				mutableIterator.add("$indent$replacement")
			}
		}
	}
	
	return resultLines.joinToString(separator = "\n")
}

fun String.deleteLineIfContains(other: String): String {
	val resultLines = lines().toMutableList()
	val mutableIterator = resultLines.listIterator()
	
	while (mutableIterator.hasNext()) {
		val line = mutableIterator.next()
		
		if (line.contains(other)) {
			mutableIterator.remove()
		}
	}
	
	return resultLines.joinToString(separator = "\n")
}