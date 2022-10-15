package com.lauruscorp.sexify_data.data_sources.mapping

import com.lauruscorp.sexify_data.data_sources.text.getText
import com.lauruscorp.sexify_data.databases.tasks.TasksDatabase
import com.lauruscorp.sexify_data.databases.tasks.aliases.DbTask
import com.lauruscorp.sexify_data.databases.tasks.utils.getDoerPartnerSexes
import com.lauruscorp.sexify_data.databases.tasks.utils.getDoerSexes
import com.lauruscorp.sexify_data.databases.tasks.utils.getTaskStage
import com.lauruscorp.sexify_data.databases.tasks.utils.selectTranslationsByTextContentId
import com.lauruscorp.sexify_data.entities.Task

internal fun DbTask.toTask(
    database: TasksDatabase,
    useTransaction: Boolean = true
): Task? {
    fun map(): Task? {
        return Task(
            id = id,
            text = database.getText(textId) ?: return null,
            textTranslations = database.selectTranslationsByTextContentId(textId)
                .mapNotNull { it.asText(database) },
            stage = getTaskStage(database)
                ?.asTaskStage(database) ?: return null,
            doerSexes = getDoerSexes(database)
                .mapNotNull { it.toSexifySex(database) },
            partnerSexes = getDoerPartnerSexes(database)
                .mapNotNull { it.toSexifySex(database) }
        )
    }

    return if (useTransaction) {
        database.transactionWithResult {
            map()
        }
    } else {
        map()
    }
}