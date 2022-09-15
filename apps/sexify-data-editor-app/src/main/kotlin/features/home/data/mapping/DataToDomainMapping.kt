package features.home.data.mapping

import com.lauruscorp.sexifydata.database.tables.TaskDoerPartnerSex
import com.lauruscorp.sexifydata.database.tables.TaskDoerSex
import features.home.domain.entities.Sex

fun TaskDoerSex.toSex(): Sex? {
	return when (sexId) {
		0L -> Sex.Man
		1L -> Sex.Woman
		else -> null
	}
}

fun TaskDoerPartnerSex.toSex(): Sex? {
	return when (sexId) {
		0L -> Sex.Man
		1L -> Sex.Woman
		else -> null
	}
}