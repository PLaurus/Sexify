package com.lauruscorp.sexify_data.data_sources.mapping

import com.lauruscorp.sexify_data.databases.tasks.TasksDatabase
import com.lauruscorp.sexify_data.databases.tasks.aliases.DbTranslation
import com.lauruscorp.sexify_data.databases.tasks.utils.getLanguage
import com.lauruscorp.sexify_data.entities.SexifyLanguage
import com.lauruscorp.sexify_data.entities.Text

fun List<DbTranslation>.asMap(
    database: TasksDatabase
): Map<SexifyLanguage, String> {
    val result = mutableMapOf<SexifyLanguage, String>()

    for (dbTranslation in this) {
        val dbLanguage = dbTranslation.getLanguage(database) ?: continue
        val language = dbLanguage.toSexifyLanguage() ?: continue
        val translation = dbTranslation.translation
        result += language to translation
    }

    return result
}

fun DbTranslation.asText(
    database: TasksDatabase
): Text? {
    return Text(
        language = getLanguage(database)?.toSexifyLanguage() ?: return null,
        value = translation
    )
}