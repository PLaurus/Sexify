package com.lauruscorp.sexify_data.mapping

import com.lauruscorp.sexify_data.database.SexifyDatabase
import com.lauruscorp.sexify_data.database.aliases.DbTaskDoerPartnerSex
import com.lauruscorp.sexify_data.database.utils.getSex
import com.lauruscorp.sexify_data.sex.SexifySex

fun DbTaskDoerPartnerSex.toSexifySex(
	database: SexifyDatabase
): SexifySex? {
	return getSex(database)?.toSexifySex()
}