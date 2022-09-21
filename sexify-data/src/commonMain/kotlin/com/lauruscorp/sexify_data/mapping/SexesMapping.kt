package com.lauruscorp.sexify_data.mapping

import com.lauruscorp.sexify_data.sex.SexifySex
import com.lauruscorp.sexifydata.database.tables.Sex

fun Sex.toSexifySex(): SexifySex? {
	return when (enumName) {
		SexifySex.Man.name -> SexifySex.Man
		SexifySex.Woman.name -> SexifySex.Woman
		else -> null
	}
}