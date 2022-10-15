package com.lauruscorp.sexify_data.data_sources.mapping

import com.lauruscorp.sexify_data.data_sources.text.getText
import com.lauruscorp.sexify_data.databases.tasks.TasksDatabase
import com.lauruscorp.sexify_data.databases.tasks.aliases.DbTaskStage
import com.lauruscorp.sexify_data.databases.tasks.utils.selectTranslationsByTextContentId
import com.lauruscorp.sexify_data.entities.Task

fun DbTaskStage.asTaskStage(
    database: TasksDatabase
): Task.Stage? {
    return Task.Stage(
        id = id,
        name = database.getText(nameTextId) ?: return null,
        nameTranslations = database.selectTranslationsByTextContentId(nameTextId)
            .mapNotNull { it.asText(database) },
        description = descriptionTextId?.let(database::getText),
        descriptionTranslations = descriptionTextId?.let {
            database.selectTranslationsByTextContentId(descriptionTextId)
                .mapNotNull { it.asText(database) }
        } ?: emptyList()
    )
}