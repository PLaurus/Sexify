package com.lauruscorp.sexify_data.mapping

import com.lauruscorp.sexify_data.database.SexifyDatabase
import com.lauruscorp.sexify_data.database.aliases.DbTaskDoerSex
import com.lauruscorp.sexify_data.database.utils.getSex
import com.lauruscorp.sexify_data.sex.SexifySex

fun DbTaskDoerSex.toSexifySex(
	database: SexifyDatabase
): SexifySex? {
	return getSex(database)?.toSexifySex()
}