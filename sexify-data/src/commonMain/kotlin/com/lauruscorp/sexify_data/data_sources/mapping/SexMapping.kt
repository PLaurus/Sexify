package com.lauruscorp.sexify_data.data_sources.mapping

import com.lauruscorp.sexify_data.databases.tasks.aliases.DbSex
import com.lauruscorp.sexify_data.entities.SexifySex

// TODO: make internal
fun DbSex.asSexifySex(): SexifySex? {
	return when (enumName) {
		SexifySex.Man.name -> SexifySex.Man
		SexifySex.Woman.name -> SexifySex.Woman
		else -> null
	}
}

internal fun SexifySex.asDbString(): String = name

internal fun String.asSexifySex(): SexifySex? {
	return try {
		SexifySex.valueOf(this)
	} catch (_: IllegalArgumentException) {
		null
	}
}

