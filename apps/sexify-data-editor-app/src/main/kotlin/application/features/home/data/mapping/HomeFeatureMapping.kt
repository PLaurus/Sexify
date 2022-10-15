package application.features.home.data.mapping

import com.lauruscorp.sexify_data.entities.SexifyLanguage
import com.lauruscorp.sexify_data.entities.SexifySex
import com.lauruscorp.sexify_data.entities.Task
import com.lauruscorp.sexifydata.databases.tasks.tables.TaskDoerPartnerSex
import com.lauruscorp.sexifydata.databases.tasks.tables.TaskDoerSex
import features.home.domain.entities.HomeSex
import features.home.domain.entities.HomeTask

internal fun HomeSex.asSexifySex(): SexifySex {
    return when (this) {
        HomeSex.Man -> SexifySex.Man
        HomeSex.Woman -> SexifySex.Woman
    }
}

internal fun SexifySex.asHomeSex(): HomeSex {
    return when (this) {
        SexifySex.Man -> HomeSex.Man
        SexifySex.Woman -> HomeSex.Woman
    }
}

fun TaskDoerSex.toHomeSex(): HomeSex? {
    return when (sexId) {
        0L -> HomeSex.Man
        1L -> HomeSex.Woman
        else -> null
    }
}

fun TaskDoerPartnerSex.toHomeSex(): HomeSex? {
    return when (sexId) {
        0L -> HomeSex.Man
        1L -> HomeSex.Woman
        else -> null
    }
}

internal fun Task.asHomeTask(
    language: SexifyLanguage? = null
): HomeTask {
    return HomeTask(
        id = this.id,
        text = (language?.let(::getTranslatedText) ?: text).value,
        stage = this.stage.asHomeTaskStage(),
        doerSexes = this.doerSexes
            .map { it.asHomeSex() },
        partnerSexes = this.partnerSexes
            .map { it.asHomeSex() },
        timerSec = this.timerSec
    )
}

internal fun Task.Stage.asHomeTaskStage(
    language: SexifyLanguage? = null
): HomeTask.Stage {
    return HomeTask.Stage(
        id = this.id,
        name = (language?.let(::getTranslatedName) ?: name).value,
        description = (language?.let(::getTranslatedDescription) ?: description)?.value
    )
}