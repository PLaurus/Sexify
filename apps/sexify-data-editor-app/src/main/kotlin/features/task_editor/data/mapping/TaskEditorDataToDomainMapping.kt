package features.task_editor.data.mapping

import com.lauruscorp.sexifydata.database.tables.TaskDoerPartnerSex
import com.lauruscorp.sexifydata.database.tables.TaskDoerSex
import features.home.domain.entities.HomeSex

fun TaskDoerSex.toTaskEditorSex(): HomeSex? {
	return when (sexId) {
		0L -> HomeSex.Man
		1L -> HomeSex.Woman
		else -> null
	}
}

fun TaskDoerPartnerSex.toTaskEditorSex(): HomeSex? {
	return when (sexId) {
		0L -> HomeSex.Man
		1L -> HomeSex.Woman
		else -> null
	}
}